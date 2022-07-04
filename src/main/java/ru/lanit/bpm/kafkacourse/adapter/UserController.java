package ru.lanit.bpm.kafkacourse.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.bpm.kafkacourse.app.UserService;
import ru.lanit.bpm.kafkacourse.domain.User;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/send")
    public void add(@RequestBody User user) {
        userService.add(user);
    }
}
