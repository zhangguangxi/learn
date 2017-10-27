package com.zgx.design_pattern.prototypePattern;

public class ConcretePrototype12 implements Prototype1 {
    private String name;

    @Override
    public Prototype1 clone() {
        ConcretePrototype12 prototype = new ConcretePrototype12();
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
        return "Now in Prototype2,name= "+this.name;
    }
}
