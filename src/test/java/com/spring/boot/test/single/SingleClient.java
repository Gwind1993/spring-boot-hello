package com.spring.boot.test.single;

public class SingleClient {

    public static void main(String[] args) {
        //SingleThreadExecute();

        for (int i = 0; i < 20000; i++) {
            new Thread(() -> {
                DCLSingletonDemo.getInstance();
                LazySingletonDemo.lazyGetInstance();
                HungrySingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }

    private static void SingleThreadExecute() {
        for (int i = 0; i < 20; i++) {
            DCLSingletonDemo.getInstance();
            HungrySingletonDemo.getInstance();
            LazySingletonDemo.lazyGetInstance();
        }
    }

}
