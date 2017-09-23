package com.zgx.thread;

public class YieldOption {
    static class Producer extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Producer : Producer Item "+i);
                Thread.yield();
            }

        }
    }

    static class  Consumer extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 5 ;i++) {
                System.out.println("I am Consumer : Consumer Item "+i);
                Thread.yield();
            }
        }
        }

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
/*        producer.setPriority(Thread.MAX_PRIORITY);
        consumer.setPriority(Thread.MIN_PRIORITY);*/
        producer.start();
        consumer.start();
    }
}
