package com.zgx.basic;


public class Car {
    private int batch;

    public Car(int batch) {
        this.batch = batch;
    }
    public static void main(String[] args) {
        Car c1 = new Car(1);
        Car c2 = new Car(1);
        System.out.println(c1.equals(c2));
        System.out.println(c1 == c2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) {
            Car c = (Car) obj;
            return this.batch == c.batch;
        }
        return false;
    }
}
