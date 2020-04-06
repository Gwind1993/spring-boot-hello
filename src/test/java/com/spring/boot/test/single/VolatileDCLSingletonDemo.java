package com.spring.boot.test.single;

public class VolatileDCLSingletonDemo {

    private static volatile VolatileDCLSingletonDemo  instance = null;

    private VolatileDCLSingletonDemo(){

    }

    public static VolatileDCLSingletonDemo getInstance(){
        if(null == instance){
            synchronized (VolatileDCLSingletonDemo.class){
                if(null == instance){
                    instance = new VolatileDCLSingletonDemo();
                }
            }
        }
        return instance;
    }

}
