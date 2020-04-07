package com.spring.boot.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {

  Lock lock = new ReentrantLock();

  @Override
  public void run() {
    playGame();
  }

  public void playGame() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getId() + "\t invoke playGame");
      listenMusic();
    } finally {
      lock.unlock();
    }
  }

  public void listenMusic() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getId() + "\t invoke listenMusic");
    } finally {
      lock.unlock();
    }
  }

  public synchronized void sendSMS() throws Exception {
    System.out.println(Thread.currentThread().getId() + "\t invoke sendSMS");
    sendEmail();
  }

  public synchronized void sendEmail() throws Exception {
    System.out.println(Thread.currentThread().getId() + "\t invoke sendEmail");
    sendPush();
  }

  public synchronized void sendPush() throws Exception {
    System.out.println(Thread.currentThread().getId() + "\t invoke sendPush");
  }

}

/**
 * 可重入锁（也叫递归锁）
 * 指的是同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 * 在同一个线程在最外层方法获取锁的时候，在进入内层方法会自动获取锁
 * <p>
 * 也就是说，线程可以进入任何一个他已经拥有锁 所同步的代码块。
 * <p>
 * case1 synchronized
 * 12	 invoke sendSMS
 * 12	 invoke sendEmail
 * 12	 invoke sendPush
 * 13	 invoke sendSMS
 * 13	 invoke sendEmail
 * 13	 invoke sendPush
 * <p>
 * case2 reentrantLock
 * 14	 invoke playGame
 * 14	 invoke listenMusic
 * 15	 invoke playGame
 * 15	 invoke listenMusic
 */
public class ReentrantLockDemo {

  public static void main(String[] args) {
    Phone phone = new Phone();
    new Thread(() -> {
      try {
        phone.sendSMS();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, "t1").start();


    new Thread(() -> {
      try {
        phone.sendSMS();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, "t2").start();

    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("\n\n\n\n");
    Thread t3 = new Thread(phone);
    Thread t4 = new Thread(phone);

    t3.start();
    t4.start();
  }

}
