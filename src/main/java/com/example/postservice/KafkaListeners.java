package com.example.postservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "topicOne", groupId = "test")
    void listener(String data) {
        System.out.println("Listener received: " + data + "🥳" + "THIS IS FROM POST");
    }
}
