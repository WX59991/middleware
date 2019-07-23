package com.middleware.middlewarerabbitmq.springrabbitmq.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 11:21
 * @Description:
 */
@Component
public class PaymentNotifySender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sender(String msg){
        amqpTemplate.convertAndSend("notify.payment",msg);
    }

}
