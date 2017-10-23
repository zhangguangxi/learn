package com.zgx.design_pattern.single;

/**
 * 懒汉式 线程安全的  性能较低不推荐使用[每次次调用getInstance()方法都需要进行同步操作,实际上只需要保证第一次实例化的时候同步即可]
 */
public class LazySynchronizationMethodSafe {
    private static LazySynchronizationMethodSafe INSTANCE ;

    private LazySynchronizationMethodSafe() {
    }

    public static  synchronized LazySynchronizationMethodSafe getInstance(){
        if(INSTANCE == null){
            INSTANCE  = new LazySynchronizationMethodSafe();
        }
        return INSTANCE;
    }

}
