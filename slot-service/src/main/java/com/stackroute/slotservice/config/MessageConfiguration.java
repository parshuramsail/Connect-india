package com.stackroute.slotservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchange_name = "slot_exchange";

    private String order_queue = "order_queue";
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange_name);
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue orderQueue() {

        return new Queue(order_queue, false);
    }
    @Bean
    Binding bindingProduct(Queue orderQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(orderQueue()).to(directExchange).with("order_routing_key");
    }


}
