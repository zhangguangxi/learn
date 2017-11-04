package com.zgx.design_pattern.decorator;

public class ConcreteDecoratorA  extends  Decorator {

    /**
     * 构造方法，传入组件对象
     *
     * @param component
     */
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    private void operationFirst(){
        System.out.println("调用父类的operation方法之前执行的操作");
    }

    private void operationLast(){
        System.out.println("调用父类的operation方法之后需要执行的操作");
    }

    @Override
    public void operation() {
        operationFirst();
        super.operation();
        operationLast();
    }


}
