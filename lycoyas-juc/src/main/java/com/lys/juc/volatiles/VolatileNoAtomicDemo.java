package com.lys.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author Lycoyas
 * @create 2023-05-06 14:59
 * @description
 */

class MyNumber{
    volatile int number;

    public void addPlusPlus() {
        number++;
    }
}
public class VolatileNoAtomicDemo {


    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myNumber.number);
    }
}
