package com.zgx.design_pattern.adapter;

public class Adapter extends Adaptee implements  Target {
    /**
     * 由于源类Adaptee没有方法sampleoperation2()
     * 因此适配器补充上这个方法
     */
    @Override
    public void sampleOperaton2() {
        //写先关的代码
    }
}
