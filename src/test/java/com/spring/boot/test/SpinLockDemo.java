package com.spring.boot.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    AtomicReference<User> atomicReference = new AtomicReference<>();

  public static void main(String[] args) {
    SpinLockDemo spinLockDemo = new SpinLockDemo();

    new Thread(() -> {
      User user = new User("张三", 18);
      spinLockDemo.myLock(user);
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      spinLockDemo.myUnLock(user);
    }, "t1").start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    new Thread(() -> {
      User user = new User("李四", 35);
      spinLockDemo.myLock(user);
      spinLockDemo.myUnLock(user);
    }, "t2").start();
  }

  public void myLock(User user) {
    System.out.println(Thread.currentThread().getName() + "\t come in,当前用户是：" + user);
    while (!atomicReference.compareAndSet(null, user)) {
      // System.out.println(Thread.currentThread().getName()+"\t invoke myLock");
    }
  }

  public void myUnLock(User user) {
    System.out.println(Thread.currentThread().getName() + "\t invoke myUnLock");
    atomicReference.compareAndSet(user, null);

  }

}
