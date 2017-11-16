package com.zgx.design_pattern.bridgeMode;

public class Bus extends  Car {
    public Bus(Engine engine) {
        super(engine);
    }

    @Override
    public void installEngine() {
        System.out.println("Bus:");
        this.getEngine().installEngine();
    }
}
