package com.zgx.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Semap implements  Runnable {
    //许可证数量
    private static Semaphore  semaphore = new Semaphore(5);


    @Override
    public void run() {
        try {
            semaphore.acquire();
            //模拟耗时操作(处理业务)
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+"Done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        final Semap s1 = new Semap();
        ExecutorService exec = Executors.newFixedThreadPool(20);
        for (int i = 0 ;i< 20 ;i++) {
            exec.submit(s1);
        }
        exec.shutdown();


    }
}
