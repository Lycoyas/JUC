package com.lys.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Lycoyas
 * @create 2023-05-05 16:28
 * @description
 */
public class InterruptDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("程序停止");
                    break;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    //Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("--- hello interruptDemo3");

            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
    }
}
