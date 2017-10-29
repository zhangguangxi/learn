package com.zgx.interview.machineTest.processBigFile.ioMultiThread;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Biz {
    //文件块【操作文件的内容块】
    RandomAccessFile raf;
    //定义缓存大小[字节数组长度]
    private final int BUF_LEN=256;
    //读的起始点
    private long start;
    //读的结束点
    private long end;

    private Map<String,Integer> result = new HashMap<>();

    public Biz(RandomAccessFile raf, long start, long end) {
        this.raf = raf;
        this.start = start;
        this.end = end;
    }


     void bizProcess(){
        try {
            //读取的开始位置
            raf.seek(start);
            //读取的文件内容长度
            long len  = end -start;
            //需要读取的次数
            long times = len/BUF_LEN+1;
            //每次读取的字节数
            byte[] buff = new byte[BUF_LEN];
            //当前读取的字节数
            int hasRead=0;
            for (int i = 0; i < times; i++) {
                hasRead=raf.read();
                //读取到文件末尾
                if(hasRead < 0){
                    break;
                }
                raf.read(buff);
                //每次读取的内容
                String content = new String(buff, "utf-8");
                String resultContent = contentProcess(content);
                System.out.println(resultContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String contentProcess(String content) {
        StringBuffer sb = new StringBuffer();
        String[] arr = content.split("\\|");
        String regex ="\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        for (int i = 0; i < arr.length; i++) {
            String item = arr[i];
            if(item.matches(regex)){
                arr[i]=String.valueOf(Integer.valueOf(item)+1);
            }
            sb.append(arr[i]).append("|");
        }
        return sb.toString();
    }
}
