package com.middleware.middlewarerabbitmq.springrabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 15:35
 * @Description:
 */
@Component
public class ApiReportReceive {

    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void payment(String msg){
        System.out.println("api.report.payment receive message: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg){
        System.out.println("api.report.refund receive message: "+msg);
    }

}
