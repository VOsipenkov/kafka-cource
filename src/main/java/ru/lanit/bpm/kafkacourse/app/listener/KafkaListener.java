package ru.lanit.bpm.kafkacourse.app.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListener {

    public void processOrder(Object order) {
        log.info("Получено сообщение из кафки {}", order);
    }

    public void processUser(Object user) {
        log.info(user.toString());
    }
}
