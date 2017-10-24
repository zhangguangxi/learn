package com.zgx.design_pattern.factory.simplePattern;

import com.zgx.design_pattern.factory.ProductA;
import com.zgx.design_pattern.factory.ProductB;

public class Factory {
    private static  final int TYPE_A =1;
    private static  final int TYPE_B =2;

    public IProduct create(int type){
        if(type ==1 ){
            return new ProductA();
        }else if(type ==2){
            return new ProductB();
        }
        return null;
    }
}
