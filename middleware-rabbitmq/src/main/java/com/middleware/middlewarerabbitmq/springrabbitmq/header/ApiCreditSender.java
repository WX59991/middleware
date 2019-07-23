//package com.middleware.middlewarerabbitmq.springrabbitmq.header;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * @author wangxia
// * @date 2019/7/23 15:20
// * @Description:
// */
//@Component
//public class ApiCreditSender {
//
//    @Autowired
//    private AmqpTemplate rabbitTemplate;
//
//    public void creditBank(Map<String, Object> head, String msg){
//        rabbitTemplate.convertAndSend("creditBankExchange", "credit.bank", getMessage(head, msg));
//    }
//
//    public void creditFinance(Map<String, Object> head, String msg){
//        rabbitTemplate.convertAndSend("creditFinanceExchange", "credit.finance", getMessage(head, msg));
//    }
//
//
//}
