package com.zgx.interview.machineTest;

import org.apache.activemq.transport.nio.NIOBufferedInputStream;

import java.io.*;
import java.lang.reflect.Field;
import java.util.concurrent.LinkedBlockingDeque;

public class Test3 {
    public static void main(String[] args) {
        String source = "AA|BB|CC|555|DDD|666|EEE";
        System.out.println(lineProcess(source));
    }


    public static String lineProcess(String line){
        StringBuilder newLine = new StringBuilder();
        String[] items = line.split("\\|");
        for (int i = 0; i < items.length; i++) {
            if(items[i].matches("\\d+")){
                items[i] =String.valueOf(Integer.valueOf(items[i])+1);
            }
            newLine.append(items[i]).append("|");
        }
        return newLine.deleteCharAt(newLine.length()-1).toString();
    }


    class Biz{
        //读取文件
        private File readFile;
        //写入文件
        private File writeFile;
        //已读的行数
        private Long readeLines = 0L;

          LinkedBlockingDeque<String> contens = new LinkedBlockingDeque<>();

        public Biz(File readFile, File writeFile) {
            this.readFile = readFile;
            this.writeFile = writeFile;
        }

        /**
         * 获取需要读取的范围
         * @return
         */
        public synchronized Long[] getReadRange(){
            return new  Long[]{readeLines+1,readeLines+=1};
        }

        /**
         * 处理
         * @param startLine
         * @param endLine
         */
        void process(Long startLine,Long endLine){
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(readFile));
                BufferedReader in = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);
                FileWriter fw = new FileWriter(writeFile);
                while (in.ready()){
                    String line = in.readLine();
                    fw.append(line).append(System.lineSeparator());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

