package com.stackroute.userservice.config;

import com.stackroute.userservice.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

//    public Producer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
//        this.rabbitTemplate=rabbitTemplate;
//        this.directExchange=directExchange;
//    }

    public void sendMessageToRabbitMq(UserDto userDto) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user_roting_key", userDto);
        rabbitTemplate.convertAndSend(directExchange.getName(), "email_routing_key", userDto);
    }

}
