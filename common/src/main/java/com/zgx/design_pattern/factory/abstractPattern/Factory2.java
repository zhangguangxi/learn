package com.zgx.design_pattern.factory.abstractPattern;

public class Factory2 implements  IFactory {
    @Override
    public IProductA createA() {
        return new ProductA2();
    }

    @Override
    public IProductB createB() {
        return new ProductB2();
    }
}
