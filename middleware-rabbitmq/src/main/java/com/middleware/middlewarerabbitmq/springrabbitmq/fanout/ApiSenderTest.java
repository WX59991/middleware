package com.middleware.middlewarerabbitmq.springrabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 15:37
 * @Description:
 */
@Component
public class ApiSenderTest {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void generateReports(String msg){
        rabbitTemplate.convertAndSend("reportExchage","api.generate.report",msg);
    }

}
