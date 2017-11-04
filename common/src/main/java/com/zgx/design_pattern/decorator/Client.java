package com.zgx.design_pattern.decorator;

public class Client {
    public static void main(String[] args) {
        //首先创建要被装饰的原始对象(即被装饰的对象)
        ConcreteComponent c1 = new ConcreteComponent();
        Decorator decoratorA=new ConcreteDecoratorA(c1);
        decoratorA.operation();



    }
}
