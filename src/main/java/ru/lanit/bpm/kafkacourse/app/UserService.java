package ru.lanit.bpm.kafkacourse.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.lanit.bpm.kafkacourse.domain.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final KafkaTemplate<String, Object> userKafkaTemplate;

    private final String userTopic;

    public void add(User user) {
        userKafkaTemplate.send(userTopic, "", user);
    }

    public void listen(User user) {
        log.info(user.toString());
    }
}
