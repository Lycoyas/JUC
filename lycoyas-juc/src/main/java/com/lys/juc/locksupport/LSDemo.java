package com.lys.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lycoyas
 * @create 2023-05-05 16:59
 * @description
 */
public class LSDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t--come in");
            LockSupport.park();
            //LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t---被唤醒");


        }, "t1");
        t1.start();

        /*try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        new Thread(() -> {
            LockSupport.unpark(t1);
            //LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName()+"\t---发出通知");
        }, "t2").start();
    }

    void syncWaitNotify() {
        Object objectLock = new Object();



        new Thread(() -> {
            /*try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t---come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"\t---被唤醒");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName()+"\t---发出通知");
            }
        }, "t2").start();
    }

    void lockCondition() {
        Lock lock = new ReentrantLock();
        Condition condition =  lock.newCondition();

        new Thread(() -> {
            /*try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t---come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t---被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try{
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"\t---发出通知");
            }finally {
                lock.unlock();
            }
        }, "t2").start();
    }

}
