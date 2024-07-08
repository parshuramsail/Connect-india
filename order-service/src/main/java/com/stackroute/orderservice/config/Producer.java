package com.stackroute.orderservice.config;

import com.stackroute.orderservice.dto.CustomerOrderDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

    public void sendMessageToRabbitMq(CustomerOrderDto customerOrderDto) {

        CustomerOrderDto customerOrderDto1 = new CustomerOrderDto();
        customerOrderDto1.setOrderId(customerOrderDto.getOrderId());
        customerOrderDto1.setOrderDate(customerOrderDto.getOrderDate());
        customerOrderDto1.setBuyerEmail(customerOrderDto.getPlanDto().getBuyerEmail());
        customerOrderDto1.setPrice(customerOrderDto.getPlanDto().getPrice());

        rabbitTemplate.convertAndSend(directExchange.getName(), "order_routing_key", customerOrderDto1);
    }

}
