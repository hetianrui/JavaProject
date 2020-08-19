package common;

import java.io.*;

//读取文件的工具类
public class FileUtil {
    //读文件：一下把整个文件内容都读到String中
    public static String readFile(String filePath) {
        //当前涉及到的编译错误，标准输出结果等文件内容都是文本文件，采用字符流
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String s ="";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            return new String(sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    //filepath写入文件的位置
    //写入文件的内容

    public static void writeFile(String filepath,String content){
        FileWriter fw=null;
        try {
            fw=new FileWriter(filepath);
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
