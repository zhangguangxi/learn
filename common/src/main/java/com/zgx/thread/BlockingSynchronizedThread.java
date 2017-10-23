package com.zgx.thread;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingSynchronizedThread {
    /**
     * 定义一个阻塞队列用来存储生产出来的商品
     */
    private LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<Integer>();

    /**
     * 定义生产商品个数
     */
    private static  final int size  = 10;

    /**
     * 定义启动线程的标志 为0时启动，启动生产商品的线程；为1时，启动消费商品的线程
     */
    private int flag = 0;

    private class LinkBlockTread implements  Runnable{

        @Override
        public void run() {
            int new_flag = flag++;
            System.out.println("启动线程 "+new_flag);
            if(new_flag == 0){
                for (int i = 0; i < size;i++) {
                    int i1 = new Random().nextInt(255);
                    System.out.println("生产商品: "+i1 +" 号");
                    try {
                        queue.put(i1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("仓库中还有商品: "+queue.size()+"个");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                for (int i = 0; i < size/2; i++) {
                    try {
                        Integer take = queue.take();
                        System.out.println("消费者购买了 "+take+" 号商品");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("仓库中还有商品: "+queue.size()+" 个");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingSynchronizedThread blockingSynchronizedThread = new BlockingSynchronizedThread();
        LinkBlockTread linkBlockTread = blockingSynchronizedThread.new LinkBlockTread();
        Thread thread1 = new Thread(linkBlockTread);
        Thread thread2 = new Thread(linkBlockTread);
        thread2.start();
        thread1.start();

    }

}
