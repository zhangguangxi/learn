package com.zgx.thread;

public class Bank2 {
    //使用ThreadLocal类管理共享变量account
    private static ThreadLocal<Integer> account = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public void save(int money){
        account.set(account.get()+money);
    }

    public int getAccount(){
        return account.get();
    }
}
