package com.zgx.design_pattern.template;

/**
 * Created by Administrator on 2016/7/13.
 */
public abstract class Car {

    public final void driverCareProcess(){
        //检查车辆情况
        checkCar();
        //开车
        driveCar();
        //停车
        stopCar();
    }
    //检查车辆情况
    public void checkCar(){
        System.out.printf("检查车辆情况!");
    }

    //开xx车
    public abstract void driveCar();
    //停车
    public void stopCar(){
        System.out.printf("停车!");
    }

    public static void main(String[] args) {

    }
}
