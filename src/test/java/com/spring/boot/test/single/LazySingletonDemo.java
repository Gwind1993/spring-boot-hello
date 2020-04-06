package com.spring.boot.test.single;

public class LazySingletonDemo {

    private static LazySingletonDemo instance = null;

    private LazySingletonDemo(){
        System.out.println(Thread.currentThread().getName() +"\t LazySingletonDemo 初始化完成");
    }

    public static LazySingletonDemo lazyGetInstance() {
        if (null == instance) {
            instance = new LazySingletonDemo();
        }
        return instance;
    }


}
