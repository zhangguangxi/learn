package com.zgx.design_pattern.single;

/**
 * 因为在同步代码块中再次进行了if(singleton == null判断),保证了只会新建一个实例。
 * 因为使用了volatile关键字，所在在JDK1.5之后，双重检查锁定才能够正常达到单例效果。
 */
public class LazyDoubleCheckSafe {
    private static volatile  LazyDoubleCheckSafe INSTANCE;

    private LazyDoubleCheckSafe() {
    }

    public static  LazyDoubleCheckSafe getInstance(){
        if(INSTANCE == null){
            synchronized (LazyDoubleCheckSafe.class){
                if(INSTANCE == null){
                    INSTANCE = new  LazyDoubleCheckSafe();
                }
            }
        }
        return INSTANCE;
    }
}
