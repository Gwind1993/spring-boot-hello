package com.spring.boot.test;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

  public static void main(String[] args) {
    User zhangsan = new User("zhangsan", 24);
    User lisi = new User("lisi", 27);
    AtomicReference<User> atomicUser = new AtomicReference<>();
    atomicUser.set(zhangsan);
    System.out.println(atomicUser.compareAndSet(zhangsan, lisi) + "\t" + atomicUser.get().toString());
    System.out.println(atomicUser.compareAndSet(zhangsan, lisi) + "\t" + atomicUser.get().toString());
  }

}

class User {

    private String Name;
    private Integer age;

  public User() {
  }

  public User(String name, Integer age) {
    Name = name;
    this.age = age;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  @Override
  public String toString() {
    return "User{" +
        "Name='" + Name + '\'' +
        ", age=" + age +
        '}';
  }

}
