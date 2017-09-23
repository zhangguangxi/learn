package com.zgx.thread;


public class ThreadInterruptOption implements  Runnable{
    @Override
    public void run() {
        while (true){
            //processed when current thread is interrupted
            if(Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName()+"interrupted!");
//                break;
            }
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Interrupted when sleep");
                //set interrupted state,Because the interrupted state will be clear when interrupted happen
                Thread.currentThread().interrupt();
            }
            //give the choice to the other thread that have the same priority
            Thread.yield();
        }

    }
}
