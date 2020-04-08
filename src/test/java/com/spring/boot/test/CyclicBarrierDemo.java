package com.spring.boot.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() ->{
            System.out.println("*******召唤神龙*********");
        });
        for (int i = 1; i <= 7; i++) {
            final int  tempInt = i;
            new Thread(() -> {
                try {
                    System.out.println("收集到了龙珠"+tempInt+"号");
                    System.out.println(cyclicBarrier.isBroken());
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
