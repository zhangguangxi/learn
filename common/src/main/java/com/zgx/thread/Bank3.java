package com.zgx.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank3 {
    private AtomicInteger account = new AtomicInteger(100);

    public void save(int money){
        account.addAndGet(money);
    }

    public AtomicInteger getAccount(){
        return account;
    }
}
