package ru.lanit.bpm.kafkacourse.fw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProps {

    @Value("${spring.kafka.template.default-topic}")
    private String orderTopicValue;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupIdValue;
    @Value("${spring.kafka.template.resend-topic}}")
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
