package ru.lanit.bpm.kafkacourse.fw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProps {
    @Value("${spring.kafka.producer.order.topic}")
    private String orderTopicValue;

    @Value("${spring.kafka.producer.user.topic}")
    private String userTopicValue;

    @Bean
    public String orderTopic() {
        return orderTopicValue;
    }

    @Bean
    public String userTopic() {
        return userTopicValue;
    }
}
