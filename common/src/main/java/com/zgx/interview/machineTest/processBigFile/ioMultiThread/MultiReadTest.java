package com.zgx.interview.machineTest.processBigFile.ioMultiThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class MultiReadTest {
    public static void main(String[] args) {
        //起10个线程去读取指定文件
        final int DOWN_THREAD_NUM =10;
        final String OUT_FILE_NAME="C:\\Users\\Dell\\Desktop\\test1.txt";
        final String keyWords = "张无忌";
        //Jdk1.5线程辅助类，让主线程等待素有子线程执行完毕后使用的类
        CountDownLatch doneSignal = new CountDownLatch(DOWN_THREAD_NUM);
        RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
        long length = new File(OUT_FILE_NAME).length();
        System.out.println("文件总长度: "+length+ "字节");
        //每个线程应该读取的字节数
        long numPerThread = length / DOWN_THREAD_NUM;
        System.out.println("每个线程读取的字节数:"+numPerThread+"字节");
        //整个文件整除后剩余的字节数
        long left = length % DOWN_THREAD_NUM;
        for (int i = 0; i < DOWN_THREAD_NUM; i++) {
            //为每个线程打开一个输入流、一个RandomAccessFile对象
            //让每个线程分别读负责不同文件的不同部分
            try {
                outArr[i] = new RandomAccessFile(new File(OUT_FILE_NAME),"rw");
                if(i == DOWN_THREAD_NUM -1){
                    //最后一个线程读取指定numPerThread+left字节
                    System.out.println("第"+i+"个线程读取从"+i * numPerThread+"到"+((i+1)*numPerThread+left)+"的位置");
                }else{
                    //每个线负责读取一定的numberPerThread个字节
                    System.out.println("第"+i+"个线程读取从"+i*numPerThread+"到"+((i+1)*numPerThread)+"的位置");
                    new ReadThread(i * numPerThread,(i+1)*numPerThread,outArr[i],keyWords,doneSignal).start();

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = KeyWordsCount.INSTANCE.getCount();
        System.out.println("指定关键字出现的次数:"+count);
    }
}
