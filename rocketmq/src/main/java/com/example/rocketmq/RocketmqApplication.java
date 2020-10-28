package com.example.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class RocketmqApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RocketmqApplication.class, args);
        log.info("启动成功");
    }

}