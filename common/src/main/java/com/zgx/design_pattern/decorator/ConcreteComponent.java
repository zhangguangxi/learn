package com.zgx.design_pattern.decorator;

public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("具体组件对应接口的处理！");
    }
}
