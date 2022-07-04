package ru.lanit.bpm.kafkacourse.fw;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaConfig {

    //    PRODUCER
    @Bean
    @ConfigurationProperties("spring.kafka.producer.order")
    public Properties orderProducerProps() {
        return new Properties();
    }

    @Bean
    @ConfigurationProperties("spring.kafka.producer.user")
    public Properties userProducerProps() {
        return new Properties();
    }

    @Bean
    public ProducerFactory<String, Object> orderProducerFactory(
        @Qualifier("orderProducerProps") Properties orderProducerProps) {

        Map<String, Object> config = new HashMap<>();
        orderProducerProps.stringPropertyNames().forEach(p -> config.put(p, orderProducerProps.get(p)));
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public ProducerFactory<String, Object> userProducerFactory(
        @Qualifier("userProducerProps") Properties userProducerProps) {

        Map<String, Object> config = new HashMap<>();
        userProducerProps.stringPropertyNames().forEach(p -> config.put(p, userProducerProps.get(p)));
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Object> orderKafkaTemplate(
        @Qualifier("orderProducerFactory") ProducerFactory<String, Object> orderProducerFactory) {
        return new KafkaTemplate<>(orderProducerFactory);
    }

    @Bean
    public KafkaTemplate<String, Object> userKafkaTemplate(
        @Qualifier("userProducerFactory") ProducerFactory<String, Object> userProducerFactory) {
        return new KafkaTemplate<>(userProducerFactory);
    }
}
