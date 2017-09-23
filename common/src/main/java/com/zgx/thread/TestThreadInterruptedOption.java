package com.zgx.thread;

public class TestThreadInterruptedOption {
    Object o = new Object();
   public static void main(String[] args) {

/*
        Thread thread = new Thread(new ThreadInterruptOption());
        thread.start();
         try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();*/
       TestThreadInterruptedOption testThreadInterruptedOption = new TestThreadInterruptedOption();
       testThreadInterruptedOption.test();

   }

    public void test(){
        try {
            o.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
