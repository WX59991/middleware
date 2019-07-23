package com.middleware.middlewarerabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxia
 * @date 2019/7/23 15:08
 * @Description:
 */
@Configuration
public class HeaderConfig {

    @Bean
    public Queue  creditBankQueue(){
        return new Queue("credit.bank");
    }

    @Bean
    public Queue creditFinanceQueue(){
        return new Queue("credit.finance");
    }

    @Bean
    public HeadersExchange creditBankExchange(){
        return new HeadersExchange("creditBankExchange");
    }

    @Bean
    public HeadersExchange creditFinancveExchage(){
        return  new HeadersExchange("creditFinanceExchange");
    }

    @Bean
    public Binding bindingCreaditAExchange(Queue creditBankQueue,HeadersExchange creditBankExchange ){
        Map<String,Object> headerValues=new HashMap<>();
        headerValues.put("type","cash");
        headerValues.put("aging","fast");
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerValues).match();
    }

//    @Bean
//    public Binding bindingCreditBExchange(Queue creditFinanceQueue, HeadersExchange creditFinanceExchange) {
//        Map<String,Object> headerValues = new HashMap<>();
//        headerValues.put("type", "cash");
//        headerValues.put("aging", "fast");
//        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headerValues).match();
//    }

}
