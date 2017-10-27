package com.zgx.design_pattern.prototypePattern;

public class ConcretePrototype11 implements Prototype1 {
    private String name;

    @Override
    public Prototype1 clone() {
        ConcretePrototype11 prototype = new ConcretePrototype11();
        prototype.setName(this.name);
        return prototype;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Now in Prototype1,name= "+this.name;
    }
}
