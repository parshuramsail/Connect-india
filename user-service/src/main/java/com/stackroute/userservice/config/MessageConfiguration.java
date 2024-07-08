package com.stackroute.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    private String exchange_name = "user_exchange";
    private String register_queue = "user_queue";

    private String email_queue = "email_queue";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange_name);
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue registerQueue() {

        return new Queue(register_queue, false);
    }

    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(registerQueue()).to(directExchange).with("user_roting_key");
    }

    @Bean
    public Queue emailQueue() {

        return new Queue(email_queue, false);
    }

    @Bean
    Binding bindingUserForEmail(Queue emailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(emailQueue()).to(directExchange).with("email_routing_key");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessageConverter());
        return rabbitTemplate;
    }
}
