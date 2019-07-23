package com.middleware.middlewarerabbitmq.rpc;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wangxia
 * @date 2019/7/23 16:07
 * @Description:
 */
@Component
@RabbitListener(queues = "query.order")
public class QueryOderReceive {

    @RabbitHandler
    public Order receive(String orderId){
        Order order=new Order();
        order.setId(1);
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        return order;
    }

}
