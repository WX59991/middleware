package com.middleware.middlewarerabbitmq.rabbitMq.rpc;

import com.middleware.middlewarerabbitmq.rabbitMq.CommonRabbit;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author wangxia
 * @date 2019/7/23 10:25
 * @Description:
 */
public class RpcServer {

    public static void main(String[] args) throws Exception {
        Connection connection=CommonRabbit.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare("rpc_queue",false,false,false,null);
        channel.basicQos(0,1,false);
    }

}
