package com.zgx.interview.machineTest.base;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Ergodic {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);


        new Thread(new Runnable() {
            @Override
            public void run() {
                //通过Iterator遍历
                for (Integer item : list) {
                    System.out.println("遍历元素: "+item);
                }
                try {
                    // 由于程序跑的太快，这里sleep了1秒来调慢程序的运行速度
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 线程二：remove一个元素
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 由于程序跑的太快，这里sleep了1秒来调慢程序的运行速度
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                list.remove(4);
                System.out.println("list.remove(4)");
            }
        }).start();


    }



    public void ergodicWays(List<Integer> list){
        //for循环遍历list   对于RandomAccess接口是一种性能很好的遍历方式,但是对linkedList这样基于链表实现的list，通过list.get(i),获取元素的性能较差
        System.out.println("fore循环遍历:");

        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
        //迭代器遍历list
        System.out.println("迭代器遍历:");
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer next =  iterator.next();
            System.out.println(next);
        }
        //foreach
        System.out.println("foreach遍历:");
        for (Integer i : list) {
            System.out.println(i);
        }

        //jdk1.8新增foreach方法
        System.out.println("jdk1.8新增foreach遍历:");
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println();
            }
        });
        //jdk1.8 lambda表达式
        System.out.println("jdk1.8 lambda表达式");
        list.forEach(item->{
            System.out.println(item);
        });
    }



}
