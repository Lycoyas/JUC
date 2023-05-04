package com.lys.juc.locks;

/**
 * @author Lycoyas
 * @create 2023-05-04 21:13
 * @description
 */
public class LockSyncDemo {

    Object object = new Object();

    public void m1() {
        synchronized (object) {
            System.out.println("--- hello");
        }
    }

    public static void main(String[] args) {

    }

}
