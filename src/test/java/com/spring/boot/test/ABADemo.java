package com.spring.boot.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);


    public static void main(String[] args) {

        System.out.println("=================ABA问题产生======================");
        new Thread(() -> {
            System.out.println("线程t1获取到的初始值：" + atomicReference.get());
            try {
                // 确保t2取到了初始值
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 101) + "\t" + atomicReference.get());
            System.out.println(atomicReference.compareAndSet(101, 100) + "\t" + atomicReference.get());
        }, "t1").start();

        new Thread(() -> {
            System.out.println("线程t2获取到的初始值：" + atomicReference.get());
            try {
                // 确保t1执行完毕
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 1993) + "\t" + atomicReference.get());
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=================ABA问题解决======================");

        new Thread(() -> {
            System.out.println("t3 初始版本号" + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("t3 当前版本号是：" + atomicStampedReference.getStamp() + "\t当前值是：" + atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("t3 当前版本号是：" + atomicStampedReference.getStamp() + "\t当前值是：" + atomicStampedReference.getReference());
        }, "t3").start();

        new Thread(() -> {
            System.out.println("t4 初始版本号" + atomicStampedReference.getStamp());
            try {
                // 确保t1执行完毕
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }    
            boolean result = atomicStampedReference.compareAndSet(100, 2009, 1, atomicStampedReference.getStamp() + 1);
            System.out.println("t4 当前compareAndSet的执行结果是：" + result + "\t当前版本号是：" + atomicStampedReference.getStamp() + "\t当前值是：" + atomicStampedReference.getReference());
        }, "t4").start();


    }

}





































