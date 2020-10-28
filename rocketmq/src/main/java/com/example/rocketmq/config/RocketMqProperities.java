package com.example.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMqProperities {

    private String nameServer;
    private String SecretKey;
    //实例名
    private String InstanceName;

    //accessKey
    private String accessKey;
    //GroupId
    private String groupId;

    //consumer要消费的topic列表，用；分隔
    private String consumertopics;

}
