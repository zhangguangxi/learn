package com.zgx.thread;

public class ThreadWaitAndSleep implements  Runnable{
    int number = 10;

    public void secondMethod() throws  Exception{
        synchronized (this){
//            Thread.sleep(2000);
            this.wait(2000);
            number *=200;
        }
    }
    @Override
    public void run() {
        try {
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void firstMethod()throws  Exception{
        synchronized (this){
            number += 100;
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        ThreadWaitAndSleep threadWaitAndSleep = new ThreadWaitAndSleep();
        Thread thread = new Thread(threadWaitAndSleep);
        thread.start();
        try {
            threadWaitAndSleep.secondMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(threadWaitAndSleep.number);
    }
}
