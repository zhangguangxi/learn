package com.zgx.design_pattern.factory.simplePattern;

import com.zgx.design_pattern.factory.ProductA;
import com.zgx.design_pattern.factory.ProductB;

/**
 * 简单工厂模式  违背了设计模式的开闭原则 不方便扩展
 */
public class Client {
    public static void main(String[] args) {
        Factory factory = new Factory();
        ProductA productA = (ProductA)factory.create(1);
        ProductB productB = (ProductB)factory.create(2);
    }
}
