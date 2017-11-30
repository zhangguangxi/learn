/*
package com.zgx.interview.machineTest.processBigFile.ioMultiThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.concurrent.*;

public class MultiReadTestV2 {
    public static void main(String[] args) {
        try {
            bigFileProcess("C:\\Users\\Dell\\Desktop\\test1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void  bigFileProcess(String path) throws FileNotFoundException {
        //线程数量
        final int DOWN_THREAD_NUM  = 10;
        //线程计数器
        CountDownLatch countDownLatch = new CountDownLatch(DOWN_THREAD_NUM);
        RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        ExecutorService threadPool = Executors.newFixedThreadPool(DOWN_THREAD_NUM);
        File file = new File(path);
        //获取文件总字节数
        long length = file.length();
        //每个线程处理文件字节数
        long perThread = length / DOWN_THREAD_NUM;
        //偏移量
        long offSet = length % DOWN_THREAD_NUM;

        long start=0;
        long end =0;
        for (int i = 0; i < DOWN_THREAD_NUM; i++) {
            start = i*perThread;
            end = (i+1)*perThread;
            outArr[i] = new RandomAccessFile(file,"rw");
            if(offSet > 0 && i==DOWN_THREAD_NUM -1){
                //处理文件最后一部分,需要加上偏移量
                end+=offSet;
            }
            Biz biz = new Biz(outArr[i], start, end);
            threadPool.submit(new ReadThreadV2(biz,countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
        System.out.println("处理完成!");
    }
}
*/
