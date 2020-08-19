package API;

import OJ_Problems.Pro_DAO;
import OJ_Problems.Problem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ProblemServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id==null || "".equals(id)){
            selectAll(resp);
        }else{
            selectOne(Integer.valueOf(id),resp);
        }

    }
    private void selectOne(int id,HttpServletResponse resp) {
        resp.setContentType("application/json; charset=utf-8");
        Pro_DAO pro_dao=new Pro_DAO();
        Problem problem = pro_dao.selectById(id);
        problem.setTestCode("");
        String str=gson.toJson(problem);
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectAll(HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        Pro_DAO pro_dao=new Pro_DAO();
        List<Problem> list = pro_dao.selectAll();
        //把查询结果转换为JSON数据传给页面
        String string = gson.toJson(list);
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        try {
            response.getWriter().write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
