package com.zgx.design_pattern.createPattern.factoryMethodPattern;

public class Factory implements IFactory{

    @Override
    public IProduct createProduct(String type) {
        if("common".equals(type)){
            return new Product();
        }else if("extension".equals(type)){
            return new ExtensionProduct();
        }else{
            return  null;
        }

    }
}
