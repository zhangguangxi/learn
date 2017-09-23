package com.zgx.thread;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownOption implements  Runnable{
        private static final  CountDownLatch  end = new CountDownLatch(10);
    private static final  CountDownOption option = new CountDownOption();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(5)*1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws  Exception{
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0;i <10 ; i++) {
               exec.submit(option);
        }
        end.await();
        System.out.println("Fire!");
        exec.shutdown();
    }
}
