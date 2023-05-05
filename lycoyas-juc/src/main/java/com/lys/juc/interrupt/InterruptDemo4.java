package com.lys.juc.interrupt;

/**
 * @author Lycoyas
 * @create 2023-05-05 16:49
 * @description
 */
public class InterruptDemo4 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());

        System.out.println("---1");
        Thread.currentThread().interrupt();
        System.out.println("---2");

        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
    }
}
