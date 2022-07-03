package ru.lanit.bpm.kafkacourse.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.bpm.kafkacourse.app.OrderService;
import ru.lanit.bpm.kafkacourse.domain.Order;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/send")
    public void send(@RequestBody Order order) {
        orderService.send(order);
    }
}
