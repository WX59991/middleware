package com.middleware.middlewarerabbitmq.rabbitMq.fanout;

import com.middleware.middlewarerabbitmq.rabbitMq.CommonRabbit;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author wangxia
 * @date 2019/7/22 16:38
 * @Description:
 */
public class Consumer implements Runnable{

    public static void main(String[] args) {
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
    }

    static  final String EXCHANGE_NAME = "logs";
    public void run(){
        Connection connection= CommonRabbit.getConnection();
        try{
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
            while (true){}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}