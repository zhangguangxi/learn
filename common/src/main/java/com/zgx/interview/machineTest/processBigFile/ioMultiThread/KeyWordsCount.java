package com.zgx.interview.machineTest.processBigFile.ioMultiThread;

public enum KeyWordsCount {
    INSTANCE;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void addCount(int count){
        System.out.println("增加次数: "+count);
        this.count +=count;
    }
}
