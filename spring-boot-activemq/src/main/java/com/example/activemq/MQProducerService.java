package com.example.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by doublesouth on 2017/9/30.
 */
@Service
@EnableScheduling
public class MQProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(fixedDelay = 3000)
    public void send() {
        this.jmsMessagingTemplate.convertAndSend(MQConsumerService.Q_SAMPLE, "send a message");
    }
}
