package com.example.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by doublesouth on 2017/9/30.
 */
@Service
public class MQConsumerService {

    public static final String Q_SAMPLE = "sample.queue";

    @JmsListener(destination = Q_SAMPLE)
    public void receiveQueue(String message) {
        System.out.println("接收到的消息为：" + message);
    }
}
