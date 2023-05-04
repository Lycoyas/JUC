package com.lys.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * @author Lycoyas
 * @create 2023-05-04 22:36
 * @description
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        new Thread(()->{
            synchronized (objectA) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有A，希望获得B");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName()+"\t 成功获得B");
                }
            }
        },"A").start();
        new Thread(()->{
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有B，希望获得A");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName()+"\t 成功获得A");
                }
            }
        },"B").start();

    }
}
