package com.zgx.design_pattern.single;

/**
 * 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的
 * 推荐使用
 */
public class LazyInnerClassSafe {
    private LazyInnerClassSafe() {
    }

    public static class LazyInnerClassSafeInstance{
        private static final LazyInnerClassSafe INSTANCE = new LazyInnerClassSafe();
    }

    public static LazyInnerClassSafe getInstance(){
        return LazyInnerClassSafeInstance.INSTANCE;
    }
}
