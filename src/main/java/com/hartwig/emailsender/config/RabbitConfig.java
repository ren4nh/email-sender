package com.hartwig.emailsender.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable("email-ms.dlq").build();
    }

    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable("email-ms").withArgument("x-dead-letter-routing-key", "email-ms.dlq").withArgument("x-dead-letter-exchange", "").build();
    }
}
