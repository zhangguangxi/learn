package com.zgx.design_pattern.bridgeMode;

public class Test {
    public static void main(String[] args) {
        Engine2000 engine2000 = new Engine2000();
        Engine3000 engine3000 = new Engine3000();

        Bus bus = new Bus(engine2000);
        bus.installEngine();

        Jeep jeep = new Jeep(engine3000);
        jeep.installEngine();
    }
}
