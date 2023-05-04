package com.lys.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Lycoyas
 * @create 2023-05-04 17:53
 * @description
 */
public class CFAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "abc";
        });

        //System.out.println(completableFuture.get(2l,TimeUnit.SECONDS));

        System.out.println("aaaaaaaaaaa");
        //System.out.println(completableFuture.join());
        //System.out.println(completableFuture.getNow("xxx"));

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(completableFuture.complete("completeValue" )+ "\t" + completableFuture.join());

    }
}
