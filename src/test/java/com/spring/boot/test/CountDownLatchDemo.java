package com.spring.boot.test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //closeDoor();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，灭");
                countDownLatch.countDown();
            },CountryEnum.getCountryByIndex(i).getRetName()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"===========秦国一统天下");
        System.out.println(CountryEnum.valueOf("ONE").getRetName());
        System.out.println(CountryEnum.ONE);
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 学习完了，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t =================班长关门走人");
    }

}
