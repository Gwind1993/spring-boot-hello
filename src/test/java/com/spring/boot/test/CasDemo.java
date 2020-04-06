package com.spring.boot.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Cas 是什么？ === Compare and Swap
 * 比较并交换,它是一条CPU并发原语
 *
 */
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 1993) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\t" + atomicInteger.get());
    }


}
