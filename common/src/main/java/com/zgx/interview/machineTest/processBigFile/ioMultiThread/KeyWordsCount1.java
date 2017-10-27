package com.zgx.interview.machineTest.processBigFile.ioMultiThread;

public class KeyWordsCount1 {

    private KeyWordsCount1(){};
    static class InnerKeyWordsCount1{
       private  static KeyWordsCount1 INSTANCE = new KeyWordsCount1();
    }
    public static KeyWordsCount1 getInstance(){
        return InnerKeyWordsCount1.INSTANCE;
    }
}
