package ru.lanit.bpm.kafkacourse.fw;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProps {
    @Value("${spring.kafka.template.default-topic}")
    private String topicValue;

    @Bean
    public String topic() {
        return topicValue;
    }
}
