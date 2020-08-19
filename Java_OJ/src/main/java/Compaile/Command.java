package Compaile;

import common.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class Command {
    public static void run(String cmd, String stdFile, String errFile) {
        Runtime runtime = Runtime.getRuntime();
        Process exec = null;
        if(stdFile!=null){
            try {
                exec = runtime.exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream inputStream = null;
            BufferedReader br =null;
            try {
                inputStream = exec.getInputStream();
                br=new BufferedReader(new InputStreamReader(inputStream));
                String s="";
                StringBuilder sb=new StringBuilder();
                while ((s=br.readLine())!= null) {
                    sb.append(s).append("\n");
                }
                FileUtil.writeFile(stdFile,sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeStream(inputStream,br);
            }
        }

        InputStream is=null;
        BufferedReader bs=null;
        try {
            is = exec.getErrorStream();
            bs=new BufferedReader(new InputStreamReader(is));
            String s="";
            StringBuilder sb=new StringBuilder();
            while ((s=bs.readLine()) != null) {
                sb.append(s).append("\n");
            }
            FileUtil.writeFile(errFile,sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(is,bs);
        }

    }
public static void closeStream(InputStream is, BufferedReader os){
        if(is!=null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(os!=null){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
    public static void main(String[] args) {
        run("javac","d:\\std.txt","d:\\errFile.txt");
    }
}
