package ru.lanit.bpm.kafkacourse.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.kafkacourse.domain.Order;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class OrderService {

    private final KafkaTemplate<String, Object> orderKafkaTemplate;
    private final String orderTopic;

    public void send(Order order) throws ExecutionException, InterruptedException, TimeoutException {
        log.info("Отправка сообщения в кафку {}", order);
        var future = orderKafkaTemplate.send(orderTopic, "", order);
        future.get(10, TimeUnit.SECONDS);
        log.info("Message send successful");
    }

    @KafkaListener(topics = "#{orderTopic}", groupId = "#{groupId}")
    @SendTo("ProcessedOrders")
    public String processAndResend(Order order) {
        log.info("Process order {}", order);
        return order.getId() + " successfully processed";
    }
}
