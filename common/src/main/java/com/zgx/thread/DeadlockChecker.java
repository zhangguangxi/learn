package com.zgx.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockChecker {
        private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        private static final Runnable deadLock = new Runnable() {
        @Override
        public void run() {
            while(true){
                //获取死锁线程个数
                long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                //存在死锁线程
                if(deadlockedThreadIds != null && deadlockedThreadIds.length > 0){
                    //获取所有死锁线程信息
                    ThreadInfo[] deadThreadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                    //遍历所有线程信息
                    for (Thread thread : Thread.getAllStackTraces().keySet()) {
                        for (int i = 0; i <deadThreadInfos.length; i++) {
                            if(thread.getId() == deadThreadInfos[i].getThreadId()){
                                thread.interrupt();
                            }
                        }
                    }
                }
                else
                {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public static void check() {
        Thread thread = new Thread(deadLock);
        thread.setDaemon(true);
        thread.start();
    }
}
