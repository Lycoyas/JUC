package com.lys.juc.cf;

import sun.nio.cs.SingleByte;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Lycoyas
 * @create 2023-05-04 18:06
 * @description
 */
public class CFAPI2Demo {

    public static void main(String[] args) {
        
        
        
        CompletableFuture.supplyAsync(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("111");
                return 1;
        }).thenApply(f->{
            System.out.println("222");
            return f + 2;
        }).thenApply(f->{
            System.out.println("333");
            return f + 3;
        }).whenComplete((v,e)->{
            if(e==null){
                System.out.println("计算结果："+v);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
