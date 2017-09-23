package com.zgx.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable{
    public  static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            //尝试在5s中内获取到锁
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else{
                System.out.println("get lock failed!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock tl1 = new TimeLock();
        TimeLock tl2 = new TimeLock();
        Thread t1 = new Thread(tl1);
        Thread t2 = new Thread(tl2);
        t1.start();
        t2.start();
    }
}
