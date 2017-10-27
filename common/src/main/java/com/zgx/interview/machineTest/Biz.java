package com.zgx.interview.machineTest;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Biz {
    //阻塞队列(线程安全的)
    public static LinkedBlockingDeque<String> contens = new LinkedBlockingDeque<>();
    //读文件线程池
    private static ExecutorService ReadThreadPool = Executors.newFixedThreadPool(10);
    //读取文件
    private File file;
    //读入的字节流
    FileInputStream fileInputStream;
    //读入缓冲字符流
    BufferedReader reader;

    public Biz(File file) {
        this.file = file;
        multiReadeFile(file);
    }

    private void multiReadeFile(File file) {
        try {
            //字节流读入文件
             fileInputStream = new FileInputStream(file);
            //字节流转为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            //字符流转化为缓冲流
            reader = new BufferedReader(inputStreamReader);
            //通过线程池读取文件
            ReadThreadPool.execute(new multiReadFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    class multiReadFile implements  Runnable{

        @Override
        public void run() {
            //对于需要反复增加的String,使用StringBuilder能够提高效率
            StringBuilder lines = new StringBuilder();
            String line1 = "";
            int num = 0;
            synchronized (reader){
                try {
                    while((line1 =reader.readLine())!=null){
                        num++;
                        lines.append(line1);
                        //大于指定行数就插入队列
                        if(num > 10){
                            Biz.contens.put(lines.toString());
                            //清空StringBuilder
                            lines.delete(0,lines.length());
                            num = 0;
                            line1="";
                        }
                    }
                    if(num !=0){
                        Biz.contens.put(lines.toString());
                        lines.delete(0,lines.length());
                        num = 0;
                        line1 ="";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
