package com.zgx.basic;

public class BigCar extends  Car{
    int count;
    public BigCar(int batch, int count) {
        super(batch);
        this.count = count;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BigCar) {
            BigCar bc = (BigCar) obj;
            return super.equals(bc) && count == bc.count;
        }
        return false;
    }
    public static void main(String[] args) {
        Car c = new Car(1);
        BigCar bc = new BigCar(1, 20);
        System.out.println(c.equals(bc));
        System.out.println(bc.equals(c));
    }
}
