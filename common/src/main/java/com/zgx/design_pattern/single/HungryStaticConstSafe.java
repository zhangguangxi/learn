package com.zgx.design_pattern.single;

/**
 * 优点:写法简单，在类装载时完成实例化。避免了线程同步问题
 * 缺点:一定会实例化类，没有达到Lazy loading的下过。如果从未使用过这个实例，会造成内存的浪费。
 */
public class HungryStaticConstSafe {
   private static  final HungryStaticConstSafe INSTANCE = new HungryStaticConstSafe();

    private HungryStaticConstSafe() {

    }

    public static  HungryStaticConstSafe getInstance(){
        return INSTANCE;
    }
}
