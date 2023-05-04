package com.lys.juc.locks;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lycoyas
 * @create 2023-05-04 22:14
 * @description
 */
public class ReEntryLockDemo {
    public static void main(String[] args) {

        /*new Thread(() -> {
            m1();
        }, "t1").start();*/

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ---come in 外层调用");
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + "\t ---come in 内层调用");
                }finally {
                    lock.unlock();
                }

            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ---come in 外层调用");
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + "\t ---come in 内层调用");
                }finally {
                    lock.unlock();
                }

            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    static Lock lock = new ReentrantLock();

    static synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "\t--- come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t---end m1");
    }
    static synchronized void m2() {

        System.out.println(Thread.currentThread().getName() + "\t--- come in");
        m3();

    }
    static synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t--- come in");

    }



    static void reEntryM1() {
        final Object object = new Object();

        new Thread(()->{
            synchronized (object){
                System.out.println("外层");
                synchronized (object){
                    System.out.println("中层");
                    synchronized (object) {
                        System.out.println("内层");
                    }
                }
            }
        },"t1").start();
    }
}
