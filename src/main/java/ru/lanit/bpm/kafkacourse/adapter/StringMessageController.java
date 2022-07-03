package ru.lanit.bpm.kafkacourse.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.bpm.kafkacourse.app.StringMessageService;

@RestController
@RequiredArgsConstructor
public class StringMessageController {
    private final StringMessageService stringMessageService;

    @GetMapping("/message/send/{message}")
    public void send(@PathVariable("message") String message) {
        stringMessageService.send(message);
    }
}
