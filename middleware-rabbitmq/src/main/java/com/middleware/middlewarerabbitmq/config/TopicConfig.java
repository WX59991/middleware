package com.middleware.middlewarerabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author wangxia
 * @date 2019/7/23 11:31
 * @Description:  可通过通配符匹配routingKey
 */
@Configuration
public class TopicConfig {

    /**
     * 注册query
     * @return
     */
    @Bean
    public Queue coreQueue(){
        return new Queue("api.core");
    }

    @Bean
    public Queue paymentQueue(){
        return new Queue("api.payment");
    }

    /**
     * 注册交换机
     * @return
     */
    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("paymentExchange");
    }

    /**
     * 绑定
     * @param coreQueue
     * @param coreExchange
     * @return
     */
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }


}
