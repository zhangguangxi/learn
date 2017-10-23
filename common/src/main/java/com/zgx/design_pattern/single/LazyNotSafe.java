package com.zgx.design_pattern.single;

/**
 * 懒汉式 线程不安全 不可用
 */
public class LazyNotSafe {
    private static LazyNotSafe INSTANCE ;

    /**
     * 私有构造器 无参数[因为构造的是自己]
     */
    private  LazyNotSafe() {
    }

    public static LazyNotSafe getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LazyNotSafe();
        }
        return INSTANCE;
    }
}
