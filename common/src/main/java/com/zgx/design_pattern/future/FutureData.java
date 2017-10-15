package com.zgx.design_pattern.future;

public class FutureData implements  Data{
    protected  RealData realData = null;
    protected  boolean isReady = false;

    public  synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        //realData构造完成，通知getResult
        this.realData = realData;
        isReady =true;
        notifyAll();
    }

    public synchronized String getResult() {
        //一直等待，直到RealData构造完成
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
