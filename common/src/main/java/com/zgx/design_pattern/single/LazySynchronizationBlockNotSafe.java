package com.zgx.design_pattern.single;

/**
 * 懒汉式同步代码块(线程不安全、同步代码块)
 */
public class LazySynchronizationBlockNotSafe {

    private static  LazySynchronizationBlockNotSafe INSTANCE;

    private LazySynchronizationBlockNotSafe() {
    }

    public static LazySynchronizationBlockNotSafe  getInstance(){
        if(INSTANCE == null){
            /**
             * 可能同时两个线程进入,导致产生多个实例
             */
            synchronized (LazySynchronizationBlockNotSafe.class){
                INSTANCE = new LazySynchronizationBlockNotSafe();
            }
        }
        return INSTANCE;
    }
}
