package com.spring.boot.test;

import java.util.Objects;

/**
 *
 * @author Gwind
 * @since 2020.04.08
 */
public class EqualsDemo {

  public static void main(String[] args) {
    String str1 = "1234";
    String str2 = "1234";

    System.out.println(str1.equals(str2));

    String str3 = "通话";
    String str4 = "重地";
    int str3Hash = str3.hashCode();
    int str4hash = str4.hashCode();
    System.out.println("str3Hash=" +str3Hash+"\t str4Hash="+str4hash);

    // 如果不重写equals，man1.equals man2 false
    // 重写equals ，man1.equals man2 true
    Man man1 = new Man("zhangsan",12);
    Man man2 = new Man("zhangsan",12);
    System.out.println(man1.equals(man2));
  }

}

class Man{

  private String Name;
  private Integer age;

  public Man() {
  }

  public Man(String name, Integer age) {
    Name = name;
    this.age = age;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Man man = (Man) o;
    return Objects.equals(Name, man.Name) &&
        Objects.equals(age, man.age);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Name, age);
  }

}

