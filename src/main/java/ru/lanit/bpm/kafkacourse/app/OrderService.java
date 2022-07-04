package ru.lanit.bpm.kafkacourse.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.kafkacourse.domain.Order;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class OrderService {

    private final KafkaTemplate<String, Object> orderKafkaTemplate;
    private final String orderTopic;

    public void send(Order order) {
        log.info("Отправка сообщения в кафку {}", order);
        orderKafkaTemplate.send(orderTopic, "", order);
    }
}
