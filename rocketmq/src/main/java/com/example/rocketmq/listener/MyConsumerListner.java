package com.example.rocketmq.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

public class MyConsumerListner implements MessageListener {
    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        //此处可以写具体业务逻辑，body是具体发送的内容
        String body = new String(message.getBody());
        System.out.println("msgBody is : " + body);
        //返回这个则会提交
        return Action.CommitMessage;
    }
}
