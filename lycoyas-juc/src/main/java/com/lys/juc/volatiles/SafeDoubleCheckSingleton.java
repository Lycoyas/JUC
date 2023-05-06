package com.lys.juc.volatiles;

/**
 * @author Lycoyas
 * @create 2023-05-06 15:49
 * @description
 */
public class SafeDoubleCheckSingleton {
    //volatile实现线程安全的延迟初始化
    private static volatile SafeDoubleCheckSingleton singleton;

    private SafeDoubleCheckSingleton() {

    }

    public static SafeDoubleCheckSingleton getInstance() {

        if (singleton == null) {
            synchronized (SafeDoubleCheckSingleton.class) {
                if (singleton == null) {
                    //禁止初始化对象和设置singleton指向内存空间的重排序
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }

        return singleton;

    }

}
