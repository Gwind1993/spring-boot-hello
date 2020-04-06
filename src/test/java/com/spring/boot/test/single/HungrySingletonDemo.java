package com.spring.boot.test.single;

public class HungrySingletonDemo {

    private static HungrySingletonDemo instance = new HungrySingletonDemo();

    private HungrySingletonDemo(){
        System.out.println(Thread.currentThread().getName() +"\t HungrySingletonDemo 初始化完成");
    }

    public static HungrySingletonDemo getInstance(){
        return instance;
    }

}
