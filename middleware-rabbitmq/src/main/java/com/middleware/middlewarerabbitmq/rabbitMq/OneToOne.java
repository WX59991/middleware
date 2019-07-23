package com.middleware.middlewarerabbitmq.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author wangxia
 * @date 2019/7/22 11:29
 * @Description:  一个生产者 一个消费者 消费模式
 */
public class OneToOne {

    //队列名
    static final String QUEUE_NAME="one_to_one2";

    public static void main(String[] args){
        new Thread(new Consumer()).start();
        new Thread(new Consumer()).start();
        Send();
//        revice();
    }

    public static void Send(){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try(Connection connection=connectionFactory.newConnection()){
            Channel channel=connection.createChannel();
            //声明队列  队列名  持久性
            channel.queueDeclare(QUEUE_NAME,true,false, false,null);
            String message="hello World!";
            //发布消息
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            //关闭channel 不关闭会导致消费者无法获取，相对于没有提交
            channel.close();
            System.out.println("发送成功:"+message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  void revice(){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try(Connection connection=connectionFactory.newConnection()){
            Channel channel=connection.createChannel();
            //一次接受的数据量
            channel.basicQos(1);
            //声明队列
            channel.queueDeclare(QUEUE_NAME,true,false, false,null);
            DeliverCallback deliverCallback=(consumerTag, delivery)->{
                String message=new String(delivery.getBody(),"UTF-8");
                System.out.println("received:"+message);
                //手动ack
//                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            };
            //订阅主题名 自动确认(autoAck)
            channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag->{
                System.out.println("测试结束");
            });
            while (true){}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
class Consumer implements Runnable{

    public void run(){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try(Connection connection=connectionFactory.newConnection()){
            Channel channel=connection.createChannel();
            //一次接受的数据量
            channel.basicQos(1);
            //声明队列
            channel.queueDeclare(OneToOne.QUEUE_NAME,true,false, false,null);
            DeliverCallback deliverCallback=(consumerTag, delivery)->{
                String message=new String(delivery.getBody(),"UTF-8");
                System.out.println(Thread.currentThread().getName()+"received:"+message);
                //手动ack
//                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            };
            //订阅主题名 自动确认(autoAck)
            channel.basicConsume(OneToOne.QUEUE_NAME,true,deliverCallback,consumerTag->{
                System.out.println("测试结束");
            });
            while (true){}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
