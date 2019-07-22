package com.middleware.middlewarerabbitmq.rabbitMq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author wangxia
 * @date 2019/7/22 15:37
 * @Description:  公共工具类
 */
public class CommonRabbit {

    public static Connection getConnection(){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try{
            return connectionFactory.newConnection();
        }catch (Exception e){
            return null;
        }
    }

}
