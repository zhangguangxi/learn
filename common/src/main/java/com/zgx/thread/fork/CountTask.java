/*
package com.zgx.thread.fork;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long>{
    private  static   final int THRESHOLD =10000;
    private  long start;
    private  long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if(canCompute)
        {
            for (long i = start; i < end; i++) {
                 sum +=i;
            }
        } else{
            //分成10个小任务
            long step = (start + end ) /100;
            List<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                 long lastOne = pos  + step;
                 if(lastOne > end){
                     lastOne = end;
                 }
                CountTask subTask = new CountTask(pos, lastOne);
                 pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask subTask : subTasks) {
                sum+=subTask.join();
            }

        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 200000);
        ForkJoinTask<Long> result= forkJoinPool.submit(countTask);
        try {
            Long res = result.get();
            System.out.println("sum="+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
*/
