package com.middleware.middlewarerabbitmq.rpc;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wangxia
 * @date 2019/7/23 16:06
 * @Description:
 * 虽然RabbitMQ支持RPC接口调用，但不推荐使用。
 *
 * 原因：
 *
 * 1）RPC默认为单线程阻塞模型，效率极低。
 *
 * 2）需要手动实现多线程消费。
 */
@Component
public class RpcConfig {

    @Bean
    public Queue createRpc(){
        return new Queue("query.order");
    }

}
