package com.lys.juc.base;

import java.util.concurrent.Future;

/**
 * @author Lycoyas
 * @create 2023-05-03 19:34
 * @description
 */
public class DaemonDemo {

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().isDaemon()?"守护线程":"用户线程");
            while(true){

            }
            },"t1");
        t1.setDaemon(true);
        t1.start();


        System.out.println(Thread.currentThread().getName()+" 主线程");
    }
}
