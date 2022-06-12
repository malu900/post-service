package com.example.postservice.config;

import com.example.postservice.component.CommentEventModel;
import com.example.postservice.model.SentimentEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.consumer.bootstrap-servers:NOTFROMK8}")
    private String bootstrapServers;
    @Value("${spring.kafka.producer.bootstrap-servers:NOTFROMK8}")
    private String bootstrapServersprod;

    @Bean
    public ConsumerFactory<String, CommentEventModel> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                new JsonDeserializer<>(CommentEventModel.class, false).ignoreTypeHeaders().trustedPackages("*"));
    }

    @Bean(name = "KafkaListenerFactoryCommentEvent")
    public ConcurrentKafkaListenerContainerFactory<String, CommentEventModel> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CommentEventModel>
                factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    //  consumer 2


    @Bean
    public ConsumerFactory<String, SentimentEvent> consumerFactoryString() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersprod);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(SentimentEvent.class, false)
                .ignoreTypeHeaders().trustedPackages("*"));
    }


    @Bean(name = "KafkaListenerFactorySentimentEvent")
    public ConcurrentKafkaListenerContainerFactory<String, SentimentEvent>
    kafkaListenerContainerFactoryString() {
        ConcurrentKafkaListenerContainerFactory<String, SentimentEvent> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryString());
        return factory;
    }


}
