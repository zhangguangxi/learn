package com.zgx.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedThread1 {
    /**
     * 业务类
     */
    class Bank{
//      private volatile int account = 100;
//        private  int account = 100;
//        private Lock lock = new ReentrantLock();
        private  ThreadLocal<Integer> account = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 100;
            }
        };
//        public int getAccount() {
//            return account;
//        }

        //使用ThreadLocal类管理共享变量account
        /**
         * 使用同步方法实现
         * @param  money
         */
        /*public  void save(int money){
            account += money;
        }
        *//**
         * 使用同步代码块实现
         * @param  money
         *//*
        public  void save1(int money){
            synchronized (this){
                account += money;
            }
        }*/

        /**
         * 使用重入锁实现
         *
         * @param money
         */
    /*    public  void save2(int money){
            lock.lock();
            try {
                account += money;
            } finally {
                lock.unlock();
            }
        }*/

        //使用本地变量实现
        public void save3(int money){
            account.set(account.get()+money);
        }

        public int getAccount(){
            return account.get();
        }

    }

    /**
     * 线程业务
     */
    class ThreadBusiness implements Runnable{
        private Bank bank;
        private Bank2 bank2;
        private  CountDownLatch countDownLatch;
        public ThreadBusiness(Bank bank) {
            this.bank = bank;
        }

        public ThreadBusiness(Bank2 bank2) {
            this.bank2 = bank2;
        }

        public ThreadBusiness(Bank bank, CountDownLatch countDownLatch) {
            this.bank = bank;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
/*               bank.save3(1000);
               System.out.println("账户余额:"+bank.getAccount());*/
               bank2.save(1000);
               System.out.println("账户余额:"+bank2.getAccount());
//            countDownLatch.countDown();
        }
    }

    public void useThread() throws InterruptedException {
        /**
         * 创建业务对象
         */
        Bank2 bank = new Bank2();

        //创建一个初始值为5的倒数计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        /**
         * 创建线程业务对象
         */
//        ThreadBusiness newThread = new ThreadBusiness(bank,countDownLatch);
        ThreadBusiness newThread = new ThreadBusiness(bank);


        /***
         * 创建多线程
         */

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(newThread);
            thread.start();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        SynchronizedThread1 synchronizedThread = new SynchronizedThread1();
        synchronizedThread.useThread();

        long end = System.currentTimeMillis();
        System.out.println("消耗时间:"+(end-start));
    }
}
