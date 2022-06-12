package com.example.postservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaTopicConfig {
    @Bean
    @Primary
    public NewTopic codeTopic(){
        return TopicBuilder.name("sentimentPythonTopic")
                .build();
    }
}
