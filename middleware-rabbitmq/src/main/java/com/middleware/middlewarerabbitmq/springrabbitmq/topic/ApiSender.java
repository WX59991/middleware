package com.middleware.middlewarerabbitmq.springrabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 14:08
 * @Description:
 */
@Component
public class ApiSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void user(String msg) {
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
    }

    public void userQuery(String msg) {
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user.query", msg);
    }

    public void order(String msg) {
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    public void orderQuery(String msg) {
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }
}
