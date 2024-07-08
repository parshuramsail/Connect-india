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

    private String exchange_name = "user_exchange";
    private String email_queue = "email_queue";
    private  String product_email_queue ="product_email_queue";

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

        return new Queue(email_queue, false) {
        };
    }

    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(registerQueue()).to(directExchange).with("email_routing_key");
    }


    @Bean
    public Queue registerProductEmailQueue() {

        return new Queue(product_email_queue, false) {
        };
    }

    @Bean
    Binding bindingProductEmail(Queue registerProductEmailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(registerProductEmailQueue()).to(directExchange).with("email_routing_key");
    }

}
