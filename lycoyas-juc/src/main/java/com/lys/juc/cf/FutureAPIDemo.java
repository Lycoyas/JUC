package com.lys.juc.cf;

import oracle.jrockit.jfr.StringConstantPool;

import java.util.concurrent.*;

/**
 * @author Lycoyas
 * @create 2023-05-04 15:30
 * @description
 */
public class FutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        FutureTask<String> futureTask=new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName() + "\t------come in");

            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            
            return "task over";
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();


        System.out.println(Thread.currentThread().getName() + "\t------忙其他任务了");
        //System.out.println(futureTask.get(3,TimeUnit.SECONDS));

        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            }else{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("正在处理中...jjj");
            }
        }
    }
}
