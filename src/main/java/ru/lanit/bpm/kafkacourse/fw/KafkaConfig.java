package ru.lanit.bpm.kafkacourse.fw;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;
import ru.lanit.bpm.kafkacourse.app.listener.KafkaListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final KafkaListener kafkaListener;

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

    //    CONSUMER

    @Bean
    @ConfigurationProperties("spring.kafka.consumer.order")
    public Properties orderConsumerProps() {
        return new Properties();
    }

    @Bean
    @ConfigurationProperties("spring.kafka.consumer.user")
    public Properties userConsumerProps() {
        return new Properties();
    }

    @Bean
    public ConsumerFactory<String, Object> orderConsumerFactory(
        @Qualifier("orderConsumerProps") Properties orderConsumerProps) {

        Map<String, Object> config = new HashMap<>();
        orderConsumerProps.stringPropertyNames().forEach(p -> config.put(p, orderConsumerProps.get(p)));
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConsumerFactory<String, Object> userConsumerFactory(
        @Qualifier("userConsumerProps") Properties orderConsumerProps) {

        Map<String, Object> config = new HashMap<>();
        orderConsumerProps.stringPropertyNames().forEach(p -> config.put(p, orderConsumerProps.get(p)));
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public MessageListener<String, Object> orderListener() {
        return kafkaListener::processOrder;
    }

    @Bean
    public MessageListener<String, Object> userListener() {
        return kafkaListener::processUser;
    }

    @Bean
    public ConcurrentMessageListenerContainer orderMessageListenerContainer(
        Properties orderConsumerProps, ConsumerFactory<String, Object> orderConsumerFactory) {
        var containerProperties = new ContainerProperties(new String[]{(String) orderConsumerProps.get("topic")});
        containerProperties.setMessageListener(orderListener());
        containerProperties.setGroupId("first");
        return new ConcurrentMessageListenerContainer(orderConsumerFactory, containerProperties);
    }

    @Bean
    public ConcurrentMessageListenerContainer userMessageListenerContainer(
        Properties userConsumerProps, ConsumerFactory<String, Object> userConsumerFactory) {
        var containerProperties = new ContainerProperties(new String[]{(String) userConsumerProps.get("topic")});
        containerProperties.setMessageListener(userListener());
        containerProperties.setGroupId("first");
        return new ConcurrentMessageListenerContainer(userConsumerFactory, containerProperties);
    }
}
