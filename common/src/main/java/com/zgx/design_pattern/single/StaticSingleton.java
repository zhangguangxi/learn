package com.zgx.design_pattern.single;

/**
 * 满足分高并发与延迟加载条件
 *
 */
public class StaticSingleton {
    private StaticSingleton() {
        System.out.println("StaticSingleton is create");
    }

    /**
     * 静态内部类实现:声明一个静态成员变量
     */
    private static  class singletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return singletonHolder.instance;
    }
}
