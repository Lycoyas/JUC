package com.lys.juc.cf;

import java.util.concurrent.*;

/**
 * @author Lycoyas
 * @create 2023-05-04 18:21
 * @description
 */
public class CFThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "abcd";
        },threadPool).thenRunAsync(() -> {
            System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenRun(() -> {
            System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenRun(() -> {
            System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));

        threadPool.shutdown();
    }
}
