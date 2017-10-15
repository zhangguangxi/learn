package com.zgx.design_pattern.single;

/**
 * 何时产生实例不好控制
 */
public class Singleton {
    public static  int STATUS =1;
    private Singleton() {
        System.out.println("Singleton is create");
    }

    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton instance = getInstance();
        System.out.println(instance.STATUS);
    }
}
