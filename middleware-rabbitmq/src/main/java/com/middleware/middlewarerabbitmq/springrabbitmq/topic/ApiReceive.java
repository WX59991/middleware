package com.middleware.middlewarerabbitmq.springrabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 14:02
 * @Description:
 */
@Component
public class ApiReceive {

    @RabbitHandler
    @RabbitListener(queues="api.core")
    public void user(String msg){
        System.out.println("api.core receive message:"+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void order(String msg){
        System.out.println("api.payment.order receive message:"+msg);
    }

}
