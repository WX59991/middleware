package com.middleware.middlewarerabbitmq.springrabbitmq.header;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 15:17
 * @Description:
 */
@Component
public class ApiCreditReceive {

    @RabbitHandler
    @RabbitListener(queues = "credit.bank")
    public void creditBank(String msg){
        System.out.println("credit.bank receive message: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "credit.finance")
    public void creditFinance(String msg) {
        System.out.println("credit.finance receive message: "+msg);
    }
}
