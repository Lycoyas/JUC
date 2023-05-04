package com.lys.juc.cf;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Lycoyas
 * @create 2023-05-04 16:40
 * @description
 */
public class CFUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("---1秒钟后出结果");
            return result;
        },threadPool).whenComplete((v,e)->{
            if (e == null) {
                System.out.println("---计算完成,更新系统："+v);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println("异常情况: " + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");

        threadPool.shutdown();
    }

    static void future1() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("---1秒钟后出结果");
            return result;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
        System.out.println(completableFuture.get());
    }
}
