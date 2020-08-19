package API;

import Compaile.Answer;
import Compaile.Question;
import Compaile.Task;
import OJ_Problems.Pro_DAO;
import OJ_Problems.Problem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CompileServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    //借助这个类去解析body中的数据请求
    @Setter
    @Getter
    @ToString
    static class CompileRequest{
    private int id;
    private String code;
    }
    //借助这个类去构建最终的响应数据
    @Setter
    @Getter
    @ToString
    static class CompileResponse{
    private int ok;
    private String reason;
    private String stdout;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.读取请求体
        String str=readBody(req);
        //2.解析JSON数据，封装到CompileRequest对象
        CompileRequest compileRequest = gson.fromJson(str,CompileRequest.class);
        //3.按照id从数据库中读取对应的测试用例
        Problem problem = new Pro_DAO().selectById(compileRequest.getId());
        String userCode=compileRequest.getCode();
        String testCode=problem.getTestCode();
        //4.把用户输入的代码和测试用例代码进行组装，组成一个完整的可编译代码
        String finalStr=merge(userCode,testCode);
        //5.创建Task对象对刚才组装好的代码进行编译运行
        Question question=new Question();
        question.setCode(finalStr);
        question.setStdin("");
        CompileResponse compileResponse=new CompileResponse();
        Answer answer=Task.compileAndRun(question);
        compileResponse.setOk(answer.getError());
        compileResponse.setReason(answer.getReason());
        compileResponse.setStdout(answer.getStdout());
        //6.把运行结果构成响应数据，并写给客户端
        String json=gson.toJson(compileResponse);
        resp.getWriter().write(json);

    }

    private String merge(String userCode, String testCode) {
        //1.删掉模板代码最后一个大括号
        StringBuilder sb=new StringBuilder(userCode);
        sb.deleteCharAt(sb.length()-1);
        //2.把测试的main方法拼接过去
        sb.append(testCode);
        return new String(sb);
    }

    private String readBody(HttpServletRequest req) {
        int contentLength = req.getContentLength();
        byte str[]=new byte[contentLength];
        InputStream inputStream=null;
        try {
            inputStream = req.getInputStream();
            inputStream.read(str,0,contentLength);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(str);
    }
}
