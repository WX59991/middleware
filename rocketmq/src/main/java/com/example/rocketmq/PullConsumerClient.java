package com.example.rocketmq;

import com.aliyun.openservices.ons.api.*;

import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PullConsumerClient {

    public static void main(String[] args){
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, "GID_Log_Syn");
        // AccessKey ID 阿里云身份验证，在阿里云 RAM 控制台创建。
        properties.put(PropertyKeyConst.AccessKey, "JuvyIgjSHUubIjgj");
        // AccessKey Secret 阿里云身份验证，在阿里云 RAM 控制台创建。
        properties.put(PropertyKeyConst.SecretKey, "yDbKV8kjejmvkfFC0nIWjor1cIvIkD");
        // 设置 TCP 接入域名，进入消息队列RocketMQ版控制台的实例详情页面的 TCP 协议客户端接入点区域查看。
        properties.put(PropertyKeyConst.NAMESRV_ADDR, "http://MQ_INST_1277798577572919_BdSHI8dY.cn-xixian-xxunicom-d01.mq.namesrv.res.develop.xixian.unicom.local:9876");
//        properties.setProperty(PropertyKeyConst.INSTANCE_ID,"MQ_INST_1277798577572919_BdSHI8dY");
        PullConsumer consumer = ONSFactory.createPullConsumer(properties);
        // 启动 Consumer。
        consumer.start();
        // 获取 topic-xxx 下的所有分区。
        Set<TopicPartition> topicPartitions = consumer.topicPartitions("log_syn");
        // 指定需要拉取消息的分区。
        consumer.assign(topicPartitions);

        while (true) {
            // 拉取消息，超时时间为 3000 ms。
            List<Message> messages = consumer.poll(3000);
            System.out.printf("Received message: %s %n", messages);
        }
    }

}
