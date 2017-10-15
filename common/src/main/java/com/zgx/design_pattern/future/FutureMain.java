package com.zgx.design_pattern.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构建FutureTask
        FutureTask<String> future = new FutureTask<>(new RealData1("a"));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //执行FutureTask,相当于上例中的client.request("a")发送请求
        //在这里开启线程进行RealData的call执行
        executor.submit(future);
        System.out.println("请求完毕");
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据="+future.get());
    }
}
