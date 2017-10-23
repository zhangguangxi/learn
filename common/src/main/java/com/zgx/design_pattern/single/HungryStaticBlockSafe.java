package com.zgx.design_pattern.single;

public class HungryStaticBlockSafe {
    private static HungryStaticBlockSafe INSTANCE;

    private HungryStaticBlockSafe(){

    }

    static {
        INSTANCE = new HungryStaticBlockSafe();
    }

    public static  HungryStaticBlockSafe getInstance(){
        return INSTANCE;
    }
}
