package com.zgx.design_pattern.factory.abstractPattern;

import com.zgx.design_pattern.factory.simplePattern.IProduct;

public interface IFactory {
    IProductA createA();
    IProductB createB();
}
