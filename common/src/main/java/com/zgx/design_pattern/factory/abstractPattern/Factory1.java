package com.zgx.design_pattern.factory.abstractPattern;

public class Factory1 implements  IFactory {
    @Override
    public IProductA createA() {
        return new ProductA1();
    }

    @Override
    public IProductB createB() {
        return new ProductB1();
    }
}
