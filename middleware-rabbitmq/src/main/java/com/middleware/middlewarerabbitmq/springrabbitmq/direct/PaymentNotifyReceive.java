package com.middleware.middlewarerabbitmq.springrabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 11:19
 * @Description:
 */
@Component
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceive {

    @RabbitHandler
    public void receive(String msg){
        System.out.println("notify.payment receive msg:"+msg);
    }

}
