package com.stackroute.config;

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

    private String slot_queue = "slot_queue";
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange_name);
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public Queue slotQueue() {

        return new Queue(slot_queue, false);
    }
    @Bean
    Binding bindingSlot(Queue slotQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(slotQueue()).to(directExchange).with("slot_routing_key");
    }


}
