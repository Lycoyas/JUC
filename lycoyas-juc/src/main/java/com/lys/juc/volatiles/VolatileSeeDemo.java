package com.lys.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author Lycoyas
 * @create 2023-05-06 14:35
 * @description
 */
public class VolatileSeeDemo {
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t---come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + "\t---flag被设置为false，程序停止");

        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
        System.out.println(Thread.currentThread().getName()+"\t 修改完成");
    }
}
