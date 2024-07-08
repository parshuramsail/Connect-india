package com.stackroute.productservice.config;

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


    private String exchange_name = "product_exchange";
    private String product_queue = "product_queue";

    private String product_email_queue = "product_email_queue";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange_name);
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public Queue productQueue() {

        return new Queue(product_queue, false);
    }

    @Bean
    public Queue productEmailQueue() {

        return new Queue(product_email_queue, false);
    }


    @Bean
    Binding bindingProduct(Queue productQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(productQueue()).to(directExchange).with("product_routing_key");
    }

    @Bean
    Binding bindingProductForEmail(Queue productEmailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(productEmailQueue()).to(directExchange).with("product_email_routing_key");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessageConverter());
        return rabbitTemplate;
    }



}
