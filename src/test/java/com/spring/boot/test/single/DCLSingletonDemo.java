package com.spring.boot.test.single;

public class DCLSingletonDemo {

    private static DCLSingletonDemo instance = null;

    private DCLSingletonDemo(){
        System.out.println(Thread.currentThread().getName() +"\t DCLSingletonDemo 初始化完成");
    }

    /**
     * Double Check Lock 双重校验检索机制,不能保证线程完全安全
     * 因为 instance 实例化一般分为三步：
     * 1、分配内存空间
     * 2、完成对象初始化
     * 3、将分配的内存空间地址指向对象
     * 在多线程的环境中，因指令重排，导致1、2、3不一定能按顺序执行，因为1、2、3之间没有数据依赖性
     * 当通过指令重排后，执行顺序有可能成为1、3、2，这就会导致 instance != null 成立，
     * 但是instance 还未初始化完成，这就导致线程安全问题的出现
     */
    public static DCLSingletonDemo getInstance(){
        if(null == instance){
            synchronized (DCLSingletonDemo.class){
                if(null == instance){
                    instance = new DCLSingletonDemo();
                }
            }
        }
        return instance;
    }

}
