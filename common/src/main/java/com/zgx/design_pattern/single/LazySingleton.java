package com.zgx.design_pattern.single;

/**
 * 延迟加载(使用到的时候才会用到)
 * 缺点:高并发的时候并不是性能最高的
 */
public class LazySingleton {
    private LazySingleton(){
        System.out.println("LazySingleton is create");
    }

    private static LazySingleton instance = null;
    public static  synchronized  LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return  instance;
    }
}
