package com.spring.boot.test;

public class TransferValue {

    public static void main(String[] args) {
        TransferValue test = new TransferValue();
        int age = 10;
        test.changeAge(age);
        System.out.println(age);

        Person person = new Person("aaa");
        test.changeName(person);
        System.out.println(person.getName());


        String str = "abc";
        test.changStr(str);
        System.out.println(str);
    }

    public void changeAge(Integer age){
        age = 30;
    }

    public void changeName(Person person){
        person.setName("XXX");
    }

    public void changStr(String str){
        str = "xxx";
    }

}


class Person{

    private Integer age;
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}