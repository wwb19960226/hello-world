package com.bo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo {
    /**
     * 主程序启动内置tomcat
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Demo.class,args);
    }
}
