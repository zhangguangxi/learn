package com.zgx.design_pattern.bridgeMode;

public class Jeep extends  Car {
    public Jeep(Engine engine) {
        super(engine);
    }

    @Override
    public void installEngine() {
        System.out.println("Jeep:");
        this.getEngine().installEngine();
    }
}
