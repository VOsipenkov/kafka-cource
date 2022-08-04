package ru.lanit.bpm.kafkacourse.fw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProps {

    @Value("${spring.kafka.template.defaultTopic}")
    private String orderTopicValue;
    @Value("${spring.kafka.consumer.groupId}")
    private String groupIdValue;
    @Value("${spring.kafka.template.resendTopic}}")
    private String processedOrderTopicValue;

    @Bean
    public String orderTopic() {
        return orderTopicValue;
    }

    @Bean
    public String resendTopic() {
        return processedOrderTopicValue;
    }

    @Bean
    public String groupId() {
        return groupIdValue;
    }
}
