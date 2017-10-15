package com.zgx.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnsafeArrayList {
    static List list = Collections.synchronizedList(new ArrayList());

    static class AddTask implements  Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10000; i++) {
                 list.add(new Object());
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new AddTask(), "t1");
        Thread t2 = new Thread(new AddTask(), "t2");

        t1.start();
        t2.start();
    }

}
