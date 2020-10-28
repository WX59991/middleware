//package com.example.rocketmq.listener;
//
//import com.aliyun.openservices.ons.api.Message;
//import com.aliyun.openservices.ons.api.ONSFactory;
//import com.aliyun.openservices.ons.api.PullConsumer;
//import com.aliyun.openservices.ons.api.TopicPartition;
//import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.impl.consumer.PullAPIWrapper;
//import com.example.rocketmq.config.RocketMqConfig;
//import com.example.rocketmq.config.RocketMqProperities;
//import lombok.Data;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//
//@Data
//@Component
//public class PullListener  implements ApplicationListener {
//
//    @Resource
//    private RocketMqProperities rocketMqProperities;
//
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        Properties consumerProperties = RocketMqConfig.createConsumerProperties(rocketMqProperities);
//        PullConsumer pullConsumer = ONSFactory.createPullConsumer(consumerProperties);
//        //注册要监听的topic
//        String[] topicandtags = rocketMqProperities.getConsumertopics().split(";");
//        Set<TopicPartition> topicPartitions=null;
//        pullConsumer.start();
//        for(String topicmsg:topicandtags){
//            String[] split = topicmsg.split(":");
//            topicPartitions = pullConsumer.topicPartitions(split[0]);
//        }
//        pullConsumer.assign(topicPartitions);
//        new Thread(new ThreadTest(pullConsumer)).start();
//    }
//}
//class ThreadTest implements Runnable{
//
//    PullConsumer pullConsumer;
//
//    public ThreadTest(PullConsumer pullConsumer){
//        this.pullConsumer=pullConsumer;
//    }
//
//    @Override
//    public void run() {
//        while (true){
//            List<Message> poll = pullConsumer.poll(5000L);
//            System.out.println("数据量："+poll.size());
//            for(Message data:poll){
//                System.out.println("数据:"+new String(data.getBody()));
//            }
//            try {
//                Thread.sleep(30000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
