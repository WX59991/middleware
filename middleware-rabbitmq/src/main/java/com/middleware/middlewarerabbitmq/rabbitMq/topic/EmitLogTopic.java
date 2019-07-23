package com.middleware.middlewarerabbitmq.rabbitMq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author wangxia
 * @date 2019/7/22 16:28
 * @Description:  多个主题可以指定一个路由Key  消费者消费对应的key即可  但是routekey可通过正则匹配
 */
public class EmitLogTopic {


    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        argv=new String[]{"info"};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //声明direct
            //exchange name,type [durable(持久化) false  autoDelete(自动删除)false arguments(Map) null]
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            //创建队列
            //quueuename durable exclusice  autodelete arguments
            channel.queueDeclare("info",true,false,false,null);
            channel.queueDeclare("error",true,false,false,null);
            channel.queueDeclare("debuge",true,false,false,null);

            //绑定队列
            //queuename exchangename routingKey arguments
            channel.queueBind("info",EXCHANGE_NAME,"#_log",null);
            channel.queueBind("error",EXCHANGE_NAME,"#_log",null);
            channel.queueBind("debuge",EXCHANGE_NAME,"#_log",null);
            String severity = getSeverity(argv);
            String message = getMessage(argv);

            //exchangename  routingkey
            channel.basicPublish(EXCHANGE_NAME, "all_log", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }

    private static String getSeverity(String[] strings) {
        if (strings.length < 1)
            return "info_log";
        return "error_log";
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 2)
            return strings[0]+"Hello World!";
        return joinStrings(strings, " ", 1);
    }

    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0) return "";
        if (length <= startIndex) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }

}
