package com.example.postservice;

import com.example.postservice.component.CommentEventModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
//public class KafkaListeners {
//    @KafkaListener(topics = "topicTwo", groupId = "test")
//    void listener(CommentEventModel data) {
//        System.out.println("Listener received: " + data.getMessage() + data.getId() + "🥳" + "THIS IS FROM POST");
//    }
//}
