package com.lys.juc.cf;

import java.util.concurrent.*;

/**
 * @author Lycoyas
 * @create 2023-05-04 15:08
 * @description
 */
public class FutureThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        m2();

    }

    static void m2() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1=new FutureTask<>(
                ()->{
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "task1 over";
                }
        );

        threadPool.submit(futureTask1);
        FutureTask<String> futureTask2=new FutureTask<>(
                ()->{
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "task1 over";
                }
        );

        threadPool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime=System.currentTimeMillis();

        threadPool.shutdown();
        System.out.println(endTime-startTime);
    }

    static void m1(){
        long startTime = System.currentTimeMillis();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime=System.currentTimeMillis();

        System.out.println(endTime-startTime);

        System.out.println(Thread.currentThread().getName()+"\t========end");
    }

}
