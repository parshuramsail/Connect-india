package com.stackroute.config;

import com.stackroute.dto.SlotDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @RabbitListener(queues = "slot_queue")
    public void getPlanFromRabbitMq(SlotDto slotDto) {


    }
}
