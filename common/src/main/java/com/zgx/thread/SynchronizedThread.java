package com.zgx.thread;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SynchronizedThread {
    /**
     * 业务类
     */
    class Bank{
        private int account = 100;

        public int getAccount() {
            return account;
        }

        /**
         * 使用同步方法实现
         * @param  money
         */
        public synchronized void save(int money){
            account += money;
        }
        /**
         * 使用同步代码块实现
         * @param  money
         */
        public  void save1(int money){
            synchronized (this){
                account += money;
            }
        }
    }

    /**
     * 线程业务
     */
    class ThreadBusiness implements Runnable{
        private Bank bank;
        private CountDownLatch countDownLatch;

        public ThreadBusiness(Bank bank) {
            this.bank = bank;
        }

        public ThreadBusiness(Bank bank, CountDownLatch countDownLatch) {
            this.bank = bank;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
               bank.save1(1000);
               System.out.println("账户余额:"+bank.getAccount());
            countDownLatch.countDown();
        }
    }

    public void useThread() throws InterruptedException {
        /**
         * 创建业务对象
         */
        Bank bank = new Bank();

        //创建一个初始值为5的倒数计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        /**
         * 创建线程业务对象
         */
        ThreadBusiness newThread = new ThreadBusiness(bank,countDownLatch);

//        List<Thread> threads = new ArrayList<>();

        /***
         * 创建多线程
         */

        for (int i = 0; i < 5; i++) {

            Thread thread = new Thread(newThread);
            thread.start();
//          threads.add(thread);
        }
        countDownLatch.await();

//        for (Thread thread : threads) {
//            thread.join();
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        SynchronizedThread synchronizedThread = new SynchronizedThread();
        synchronizedThread.useThread();

        long end = System.currentTimeMillis();
        System.out.println("消耗时间:"+(end-start));
    }
}
