package com.middleware.middlewarerabbitmq;

import com.middleware.middlewarerabbitmq.rpc.QueryOrderSender;
import com.middleware.middlewarerabbitmq.springrabbitmq.direct.PaymentNotifySender;
import com.middleware.middlewarerabbitmq.springrabbitmq.fanout.ApiSenderTest;
import com.middleware.middlewarerabbitmq.springrabbitmq.topic.ApiSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MiddlewareRabbitmqApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext=SpringApplication.run(MiddlewareRabbitmqApplication.class, args);
        System.out.println("启动成功");
        //direct
        PaymentNotifySender paymentNotifySender=applicationContext.getBean(PaymentNotifySender.class);
        paymentNotifySender.sender("支付订单号："+System.currentTimeMillis());
        //topic
        ApiSender apiSender=applicationContext.getBean(ApiSender.class);
        apiSender.order("订单管理！");
        apiSender.orderQuery("查询订单信息!");
        apiSender.user("用户管理!");
        apiSender.userQuery("查询用户信息!");
        //fanout
        ApiSenderTest apiSenderTest=applicationContext.getBean(ApiSenderTest.class);
        apiSenderTest.generateReports("开始生成报表！");

        //rpc
        QueryOrderSender queryOrderSender=applicationContext.getBean(QueryOrderSender.class);
        queryOrderSender.sender("123456");

    }

}
