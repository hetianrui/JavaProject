package Compaile;

import common.FileUtil;

import java.io.File;

//一次执行编译运行的过程
public class Task {
    //编译运行中产生的临时文件，约定他们的名字
    //所有的临时文件都放到tmp目录中
    private static final String WORK_DIR=".\\Java_OJ\\src\\tmp\\";
    //要编译的代码的类名
    private static final String CLASS="Solution";
    //要编译的代码对应的文件名，需要和类名一致。
    private static final String CODE=WORK_DIR+"Solution.java";
    //标准输入对应的文件
    private static final String STDIN=WORK_DIR+"stdin.txt";
    //标准输出对应的文件（编译执行的代码的结果保存在这个文件中）
    private static final String STDOUT=WORK_DIR+"stdout.txt";
    //标准错误对应的文件（编译执行的代码的结果保存到这个文件中）
    private static final String STDERR=WORK_DIR+"stderr.txt";
    //编译错误对应的文件（编译出错时的具体原因）
    private static final String COMPILE_ERROR=WORK_DIR+"compile_error.txt";
    public static Answer compileAndRun(Question question){
        //0.先创建好存放的临时文件目录
        File workDir=new File(WORK_DIR);
        if(!workDir.exists()){
            workDir.mkdirs();
        }
        //1.根据编译对象Question，构造一些需要的临时文件
        FileUtil.writeFile(CODE,question.getCode());
        FileUtil.writeFile(STDIN,question.getStdin());
        //2.构造编译命令，并执行
        // 编译命令如： javac -encoding utf8 ./tmp/Solution.java -d./tmp/
        // 直接通过字符串拼接，有的时候太复杂，会丢失空格之类的
        String cmd =String.format("javac -encoding utf8 %s -d %s",CODE,WORK_DIR);
        System.out.println("编译的命令："+cmd);
        Command.run(cmd,STDIN,COMPILE_ERROR);
        String s=FileUtil.readFile(COMPILE_ERROR);
        Answer answer=new Answer();
        if(!"".equals(s)){
            //编译出错
            System.out.println("编译出错");
            answer.setError(1);
            answer.setReason(s);
            return answer;
        }

        //3.构造运行命令java -classpath ./tmp/ Solution 为了能让java命令找到正确类的对应.class文件，需要指定他的加载路径 -classpath
        cmd=String.format("java -classpath %s %s",WORK_DIR,CLASS);
        Command.run(cmd,STDOUT,STDERR);
        String ss=FileUtil.readFile(STDERR);
        if(!"".equals(ss)){
            System.out.println("运行出错");
            answer.setReason(FileUtil.readFile(STDERR));
            answer.setError(2);
            answer.setStderr(FileUtil.readFile(STDERR));
            answer.setStdout(null);
            return answer;
        }
        //将运行的最终结果放在answer里
        answer.setStdout(FileUtil.readFile(STDOUT));
        answer.setError(0);
        return answer;
    }

    public static void main(String[] args) {
        Question question=new Question();
        question.setCode(
                "public class Solution{\n" +
                "public static void main(String[] args){\n" +
                "System.out.println(10/0);\n" +
                "}\n"+
                "}"
        );
        question.setStdin(FileUtil.readFile(CODE));
        System.out.println(compileAndRun(question));

    }
}
