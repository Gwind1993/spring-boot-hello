package com.spring.boot.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {

  AtomicInteger atomicInteger = new AtomicInteger();
    volatile int num = 0;

  public void addAtomicInteger() {
    atomicInteger.getAndIncrement();
  }

  public void addPlusPlus() {
    num++;
  }

  public void addTo60() {
    this.num = 60;
  }

}

/**
 * Volatile的测试
 * Volatile是虚拟机的轻量级的同步机制，有三大特性，1，保证可见性，2、不保证原子性，3、禁止指令重排。
 */
public class VolatileDemo {

  public static void main(String[] args) {
    //atomicNotByVolatile();
    seeOkByVolatile();
  }

  /**
   * 验证volatile的可见性
   * 可见性：在多个线程同时对某个变量进行修改时，其中一个线程修改了变量值后将其写入主内存，供其他线程获取变量的当前值；
   * 主内存（堆）是所有线程共享的
   */
  private static void seeOkByVolatile() {
    MyData mydata = new MyData();
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "\t come in");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      mydata.addTo60();
      System.out.println(Thread.currentThread().getName() + "\t myDate num value:" + mydata.num);
    }, "AAA").start();

    while (mydata.num == 0) {
      //System.out.println(Thread.currentThread().getName()+"线程知道的 myData value："+mydata.num);
    }

    System.out.println("main execute over! \t " + mydata.num);
  }

  /**
   * volatile 不保证原子性
   * why ？ 多线程在执行的过程中，出现了丢失写值的现象
   */
  private static void atomicNotByVolatile() {
    MyData myData = new MyData();
    for (int i = 0; i < 20; i++) {
      new Thread(() -> {
        for (int j = 0; j < 1000; j++) {
          myData.addPlusPlus();
          myData.addAtomicInteger();
        }
      }, String.valueOf(i)).start();
    }
    while (Thread.activeCount() > 2) {
      Thread.yield();
    }
    System.out.println("int type,main finally num value:" + myData.num);
    System.out.println("atomicInteger type,main finally num value:" + myData.atomicInteger);
  }

}
