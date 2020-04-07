package com.spring.boot.test;

import org.hibernate.internal.IteratorImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ApplicationTest {

  public static void main(String[] args) {
    int a = 2;
    int b = 3;
    //System.out.println(2 > 3 ? 2 : 3);


    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");

    ListIterator<String> listIterator = list.listIterator();
    listIterator.set("5");
    while (listIterator.hasNext()){
      System.out.println(listIterator.next());

    }
  }

}
