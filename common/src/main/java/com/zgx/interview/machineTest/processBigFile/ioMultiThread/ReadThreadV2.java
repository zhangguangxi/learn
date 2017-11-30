/*
package com.zgx.interview.machineTest.processBigFile.ioMultiThread;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class ReadThreadV2 implements  Runnable {
    private Biz biz;
    private CountDownLatch cdl;

    public ReadThreadV2(Biz biz, CountDownLatch cdl) {
        this.biz = biz;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        biz.bizProcess();
        cdl.countDown();
    }
}
*/
