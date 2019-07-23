package com.middleware.middlewarerabbitmq.rabbitMq.topic;

import com.rabbitmq.client.*;

/**
 * @author wangxia
 * @date 2019/7/22 16:30
 * @Description:
 */
public class ReceiveLogsDirect implements Runnable {

    public static void main(String[] args) {
        new Thread(new ReceiveLogsDirect()).start();
        new Thread(new ReceiveLogsDirect()).start();
    }

    private static final String EXCHANGE_NAME = "topic_logs";

    public void run() {
        try {
            String[] argv = new String[]{"error_log"};
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String queueName = channel.queueDeclare().getQueue();

            if (argv.length < 1) {
                System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
                System.exit(1);
            }

            for (String severity : argv) {
                channel.queueBind(queueName, EXCHANGE_NAME, "all_log");
            }
            System.out.println(Thread.currentThread().getName()+" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(Thread.currentThread().getName()+" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
