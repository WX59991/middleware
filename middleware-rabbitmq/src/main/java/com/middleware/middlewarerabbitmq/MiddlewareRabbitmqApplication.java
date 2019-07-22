package com.middleware.middlewarerabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiddlewareRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddlewareRabbitmqApplication.class, args);
        System.out.println("启动成功");
    }

}
