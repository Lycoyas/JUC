package com.lys.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Lycoyas
 * @create 2023-05-05 16:17
 * @description
 */
public class InterruptDemo2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i < 300; i++) {
                System.out.println("---: "+i);
            }
        }, "t1");
        t1.start();

        System.out.println("t1中断标志：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.interrupt();
        System.out.println("t1调用interrupt()后的中断标志：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("t1 2s后的中断标志：" + t1.isInterrupted());

    }
}
