package com.middleware.middlewarerabbitmq.rabbitMq;

import com.rabbitmq.client.*;

/**
 * @author wangxia
 * @date 2019/7/22 14:28
 * @Description:  一个生产者多个消费者消费模式
 */
public class OneToMore {

    public static void main(String[] args) throws Exception {
//        send(5);
        new Thread(new worker()).start();
        new Thread(new worker()).start();
    }

     static final String TASK_QUEUE_NAME = "one_to_more";

    public static void  send(int size){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try(Connection connection=connectionFactory.newConnection()){
            Channel channel=connection.createChannel();
            //声明队列  队列名  持久性
            channel.queueDeclare(TASK_QUEUE_NAME,true,false, false,null);
            String message="hello World.";
            //发布消息
            for(int i=0;i<size;i++){
                channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,(message+i).getBytes());
            }
            //关闭channel 不关闭会导致消费者无法获取，相对于没有提交
            channel.close();
            System.out.println("发送成功:"+message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
class worker implements  Runnable{

    public void  run(){
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(OneToMore.TASK_QUEUE_NAME, true, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            //如果服务端未得到消费者消费完成的确认将不再发送消息给对应消费者
            //qos（服务质量保证）
            //一次最多接受的数量
            channel.basicQos(1);
            //默认情况下会平均分配，即使服务端无相应的处理能力

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(Thread.currentThread().getName()+"  [x] Received '" + message + "'");
                try {
                    OneToMore.doWork(message);
                } finally {
                    System.out.println(Thread.currentThread().getName()+" [x] Done");
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(OneToMore.TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
