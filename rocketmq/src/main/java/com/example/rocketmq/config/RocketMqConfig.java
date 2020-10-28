package com.example.rocketmq.config;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@Slf4j
@Configuration
public class RocketMqConfig {

    @Resource
    private RocketMqProperities rocketMqProperities;

    /**
     * 创建一个生产者
     * @return
     */
    @Bean("producer")
    public Producer createDefaultMQProducer() throws Exception{
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        Properties properities=new Properties();
        properities.setProperty(PropertyKeyConst.NAMESRV_ADDR,rocketMqProperities.getNameServer());
        properities.setProperty(PropertyKeyConst.SecretKey,rocketMqProperities.getSecretKey());
        properities.setProperty(PropertyKeyConst.AccessKey,rocketMqProperities.getAccessKey());
        properities.setProperty(PropertyKeyConst.INSTANCE_ID,rocketMqProperities.getInstanceName());
        properities.setProperty(PropertyKeyConst.GROUP_ID,rocketMqProperities.getGroupId());
        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        Producer producer = ONSFactory.createProducer(properities);
        producer.start();
        log.info("RocketMq defaultProducer Started.");
        return producer;
    }


    @Bean
    public Consumer creaPullConsumer() throws Exception{
        Properties properities=createConsumerProperties(rocketMqProperities);
        Consumer consumer = ONSFactory.createConsumer(properities);
        String[] topicandtags = rocketMqProperities.getConsumertopics().split(";");
        for(String topicandtag:topicandtags){
            String[] split = topicandtag.split(":");
            String topic=split[0];
            String tag="*";
            String className="";
            if(split.length==3 && !StringUtils.isEmpty(split[1])){
                tag=split[1];
                className=split[2];
            }else{
                className=split[1];
            }
            Class<MessageListener> aClass = (Class<MessageListener>)Class.forName(className);
            consumer.subscribe(topic,tag,aClass.newInstance());
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);// 延迟5秒再启动，主要是等待spring事件监听相关程序初始化完成，否则，回出现对RocketMQ的消息进行消费后立即发布消息到达的事件，然而此事件的监听程序还未初始化，从而造成消息的丢失
                    /**
                     * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
                     */
                    try {
                        consumer.start();
                    } catch (Exception e) {
                        log.info("RocketMq pushConsumer Start failure!!!.");
                        log.error(e.getMessage(), e);
                    }
                    log.info("RocketMq pushConsumer Started.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        return consumer;
    }

    public static Properties createConsumerProperties(RocketMqProperities rocketMqProperities){
        Properties properities=new Properties();
        properities.setProperty(PropertyKeyConst.NAMESRV_ADDR,rocketMqProperities.getNameServer());
        properities.setProperty(PropertyKeyConst.SecretKey,rocketMqProperities.getSecretKey());
        properities.setProperty(PropertyKeyConst.AccessKey,rocketMqProperities.getAccessKey());
        properities.setProperty(PropertyKeyConst.INSTANCE_ID,rocketMqProperities.getInstanceName());
        properities.setProperty(PropertyKeyConst.GROUP_ID,rocketMqProperities.getGroupId());
        properities.setProperty(PropertyKeyConst.ConsumeThreadNums,"1");
        properities.setProperty(PropertyKeyConst.ConsumeMessageBatchMaxSize,"10");
        return properities;
    }







}
