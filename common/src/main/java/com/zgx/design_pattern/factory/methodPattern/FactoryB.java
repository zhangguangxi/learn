package com.zgx.design_pattern.factory.methodPattern;

import com.zgx.design_pattern.factory.ProductA;
import com.zgx.design_pattern.factory.ProductB;
import com.zgx.design_pattern.factory.simplePattern.IProduct;

public class FactoryB implements IFactory {
    @Override
    public IProduct create() {
        return new ProductB();
    }
}
