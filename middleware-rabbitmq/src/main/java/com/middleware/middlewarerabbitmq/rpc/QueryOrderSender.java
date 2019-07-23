package com.middleware.middlewarerabbitmq.rpc;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 16:14
 * @Description:
 */
@Component
public class QueryOrderSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sender(String orderId){
        System.out.println("rpc query.order send message: "+orderId);
        Order order = (Order) rabbitTemplate.convertSendAndReceive("query.order", orderId);
        System.out.println("rpc query.order return message: "+order.print());
    }

}
