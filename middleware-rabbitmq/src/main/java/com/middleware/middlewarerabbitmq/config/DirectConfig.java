package com.middleware.middlewarerabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangxia
 * @date 2019/7/23 11:16
 * @Description: DirectExchange是RabbitMQ的默认交换机，直接将消息路由到所有绑定的队列中
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue paymentNotifyQueue(){
        return new Queue("notify.payment");
    }

}
