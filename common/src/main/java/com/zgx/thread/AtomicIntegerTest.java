package com.zgx.thread;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 是在使用非阻塞方法实现并发控制,在一些高并发的场合非常适合
 */
public class AtomicIntegerTest {
    static long randomTime(){
        return (long)Math.random()*1000;
    }


    public static void main(String[] args) {
        //阻塞队列能容纳1000个文件
        LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>(1000);
        //线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        File root = new File("D:\\Work");
        //完成标志
        File exitFile = new File("");
        //原子整型读个数  AtomicInteger可以在并发的情况下达到原子化的更新，避免了synchronized关键字，而且性能非常高
        AtomicInteger rc = new AtomicInteger();
        //原子整型写个数
        AtomicInteger wc = new AtomicInteger();

        //读线程
        Runnable read = new Runnable() {
            @Override
            public void run() {
                sanFile(root);
                sanFile(exitFile);
            }

            public  void sanFile(File file) {
                if(file.isDirectory()){
                        File[] files = file.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            return pathname.isDirectory() || pathname.getPath().endsWith(".txt");
                        }
                    });
                    for (File f : files) {
                        sanFile(f);
                    }
                }
                else
                {
                    //原子整型的的incrementAndGet方法,以原子的方式将当前值加1，并返回更新值
                    int index = rc.incrementAndGet();
                    System.out.println("Read: "+index+" "+file.getPath());
                    try {
                        //将文件添加到阻塞队列里面去
                        queue.put(file);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //submit方法提交一个Runnable任务用于执行，并返回一个表示任务的future
        exec.submit(read);

        for (int i = 0; i< 4;i++) {
            final int num = i;
            Runnable write = new Runnable() {
                String threadName= "Write "+num;
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(randomTime());
                            //原子整型的incrementAndGet方法，以原子的方式将当前值加1，并返回当前值
                            int index = wc.incrementAndGet();
                            //获取并移除次队列的头部，在元素变的可用之前一直等待(如果有必要)
                            File file = queue.take();
                            if(file == exitFile){
                                queue.put(exitFile);
                                break;
                            }
                            System.out.println(threadName+": "+index+" "+file.getPath());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            exec.submit(write);
        }
        exec.shutdown();
    }
}
