package com.stackroute.productservice.config;

import com.stackroute.productservice.dto.PlanDto;
import com.stackroute.productservice.dto.ProductDto;
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

    public void sendMessageToRabbitMq(ProductDto productDto) {
        //rabbitTemplate.convertAndSend(directExchange.getName(), "product_routing_key", productDto);
        rabbitTemplate.convertAndSend(directExchange.getName(), "product_email_routing_key", productDto);
    }

    public void sendMessageToRabbitMq(PlanDto planDto) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "product_routing_key", planDto);

    }
}
