package com.lys.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lycoyas
 * @create 2023-05-05 15:29
 * @description
 */
public class InterruptDemo {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("程序停止");
                    break;
                }
                System.out.println("t1-----------hello");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.interrupt();
        /*new Thread(() -> {
            t1.interrupt();
        }, "t2").start();*/
    }

    private static void m2_atomicBoolean() {
        new Thread(() -> {
            while(true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean = true,程序停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        }, "t1").start();


        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1_volatile() {
        new Thread(() -> {
            while(true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName() + "\t isStop = true,程序停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        }, "t1").start();


        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
