package com.lys.juc.locks;

import java.util.concurrent.TimeUnit;

/**
 * @author Lycoyas
 * @create 2023-05-04 20:13
 * @description
 */
public class Lock8Demo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {
            //phone.hello();
            phone2.sendSMS();
        }, "b").start();

    }

}

//资源类
class Phone{

    public static synchronized void sendEmail() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("---sendSMS");

    }


    public void hello() {
        System.out.println("---hello");
    }

}
