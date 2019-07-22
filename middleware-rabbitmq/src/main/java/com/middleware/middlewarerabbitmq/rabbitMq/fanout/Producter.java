package com.middleware.middlewarerabbitmq.rabbitMq.fanout;

import com.middleware.middlewarerabbitmq.rabbitMq.CommonRabbit;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author wangxia
 * @date 2019/7/22 16:38
 * @Description:  发布订阅模式
 * 生产者不关心具体发送到哪个订阅主题，只要订阅了都可以接受到  订阅主题会临时生成
 */
public class Producter {

    static  final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) {
        Connection connection= CommonRabbit.getConnection();
        if(connection==null){
            System.out.println("创建连接失败了");
            return;
        }
        try{
            Channel channel=connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
            String message="hello test";
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
            //不关闭程序不会停止
            channel.close();
            connection.close();
            System.out.println(message+"发送成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
