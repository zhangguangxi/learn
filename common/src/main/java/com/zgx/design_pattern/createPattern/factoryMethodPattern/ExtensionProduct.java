package com.zgx.design_pattern.createPattern.factoryMethodPattern;

public class ExtensionProduct implements IProduct {
    @Override
    public void productMethod() {
        System.out.println("this is extension product");
    }
}
