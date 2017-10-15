package com.zgx.design_pattern.createPattern.factoryMethodPattern;

public class Client {
    public static void main(String[] args) {
        IFactory factory = new Factory();
        IProduct product = factory.createProduct("extension");
        product.productMethod();
    }
}
