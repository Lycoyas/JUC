package com.lys.juc.cf;


import java.util.concurrent.*;

/**
 * @author Lycoyas
 * @create 2023-05-04 16:21
 * @description
 */
public class CFBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello";
        });
        System.out.println(completableFuture.get());

    }

    static void m1() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },threadPool);

        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }
}
