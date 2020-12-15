package com.rsjava.rabbitclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientMqController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("receive-odd-numbers")
    public String getOddNumbers() {
        Object number = rabbitTemplate.receiveAndConvert("odd-numbers");
        if (number != null) {
            log.info(number.toString());
            return number.toString();
        }
        return "empty queue of odd numbers";
    }

    @GetMapping("receive-even-numbers")
    public String getEvenNumbers() {
        Object number = rabbitTemplate.receiveAndConvert("even-numbers");
        if (number != null) {
            log.info(number.toString());
            return number.toString();
        }
        return "empty queue of even numbers";
    }
}

