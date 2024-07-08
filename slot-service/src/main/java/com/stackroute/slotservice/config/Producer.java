package com.stackroute.slotservice.config;

import com.stackroute.slotservice.dto.SlotDto;
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

    public void sendMessageToRabbitMq(SlotDto slotDto) {

        slotDto.setPrice(slotDto.getCustomerOrderDto().getPrice());
        slotDto.setOrderId(slotDto.getCustomerOrderDto().getOrderId());

        rabbitTemplate.convertAndSend(directExchange.getName(), "slot_routing_key", slotDto);
    }
}
