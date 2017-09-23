package com.zgx.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 *重入锁
 */
public class ReenterLock implements  Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0 ;j< 100000;j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args)  throws  InterruptedException{
        ReenterLock rl = new ReenterLock();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i==> "+i);
    }
}
