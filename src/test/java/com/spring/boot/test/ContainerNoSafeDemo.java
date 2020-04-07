package com.spring.boot.test;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContainerNoSafeDemo {

    public static void main(String[] args) {
        /*
         *1、new Vector<>();
         * 2、Collections.SynchronizedList(new ArrayList<>());
         * 3、java.util.concurrent.CopyOnWriteArrayList
         */
        List<String> list = new CopyOnWriteArrayList<>();


        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println("execute thread is "+Thread.currentThread().getName()+"\t"+list);
            },String.valueOf(i)).start();
        }


    }

    /**
     * 一、故障现象
     *     Java.util.ConcurrentModificationException
     * 二、导致原因
     * 多线程同时抢夺
     *
     * 三、解决方案
     *    1、用Vector
     *    2、用集合工具类Collections的synchronizedList
     *    3、java.util.concurrent.CopyOnWriteArrayList
     *
     * 四、优化建议
     */
}
