package com.example.rocketmq.controller;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@RestController
public class ProducerController {

    @Resource
    private Producer producer;

    private String topic="log_syn";
    private String tag="pv_log";

    @GetMapping("/sendStrMsg")
    public String sendStrMsg(String body){
        Message msg = new Message(topic, // topic
                tag, // tag
                UUID.randomUUID().toString(), // key
                body.getBytes());// body
        producer.send(msg);
        return "发送成功";
    }

    String jsonstr="{\"CLIENT_TYPE\": \"Android\", \"REMARK2\": \"\", \"CITY_ID\": \"365\", \"ACT_CODE\": \"我的-头部\", \"BIZ_HOSTIP\": \"10.10.34.45\", \"BIZ_PROECESS\": \"N2\\n\", \"VERSION\": \"android@7.0601\", \"MENUID\": \"0\", \"PROV_ID\": \"036\", \"UP_TYPE\": \"按钮\", \"URL_APP\": \"\", \"UP_TIME\": \"20201021165325\", \"TITLE_NAME\": \"我的金币\", \"REMARK4\": \"\", \"TRANS_ID\": \"51\", \"MOBILE\": \"13216852156\", \"JSON\": \"\", \"REMARK3\": \"\"}";
    @GetMapping("/sendJson")
    public void sendJson()  throws Exception{
        JSONObject log=JSONObject.parseObject(jsonstr);
        Message msg = new Message(topic, // topic
                tag, // tag
                UUID.randomUUID().toString(), // key
                log.toJSONString().getBytes());// body
        producer.sendAsync(msg, new SendCallback(){

            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功");
            }

            @Override
            public void onException(OnExceptionContext onExceptionContext) {
                System.out.println("发送失败");
            }
        });
    }

}
