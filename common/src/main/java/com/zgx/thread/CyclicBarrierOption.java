package com.zgx.thread;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierOption {
    public static class Soldier implements  Runnable{
        private String soldier;
        private CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            //等待所有士兵到齐
            try {
                cyclicBarrier.await();
                doWork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            //等待所有士兵完成工作
        }
        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier+" 任务完成!");
        }
    }

    public static  class BarrierRun implements  Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if(flag){
                System.out.println("司令:[士兵 +"+N + "个,任务完成]");
            }else{
                System.out.println("司令:[士兵 +"+N + "个,集合完毕]");
                flag =true;
            }
        }
    }
    public static void main(String[] args) {
        final int N  =10;
        Thread[] allSolider = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍!");
        for (int i = 0 ;i<N; i++) {
            System.out.println("士兵"+i+"报道！");
             allSolider[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier));
            allSolider[i].start();
        }

    }
}
