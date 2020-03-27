package com.spring.boot.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Application {

  public static Logger Log = LoggerFactory.getLogger(Application.class);
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    Log.info("项目启动时间：" + new Date());
  }

}
