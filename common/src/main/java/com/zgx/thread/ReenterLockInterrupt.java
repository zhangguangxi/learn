package com.zgx.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁:可中断操作
 */
public class ReenterLockInterrupt implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    private int lock;

    public ReenterLockInterrupt(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            }else{
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName()+":线程退出");
        }
    }

    public static void main(String[] args)  throws Exception{
        ReenterLockInterrupt r1 = new ReenterLockInterrupt(1);
        ReenterLockInterrupt r2 = new ReenterLockInterrupt(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        //中断一个线程
        Thread.sleep(1000);
        DeadlockChecker.check();
    }
}
