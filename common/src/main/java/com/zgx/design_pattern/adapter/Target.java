package com.zgx.design_pattern.adapter;

public interface Target {
    /**
     * 这是源类Adaptee也有的方法
     */
    public void  sampleOperaton1();

    /**
     * 这是源类Adaptee没有的方法
     */
    public void  sampleOperaton2();


}
