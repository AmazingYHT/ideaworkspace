package com.cnaidun.messageclient.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RabbitMQConnection {

    @Resource
    private RabbitMQConstants rabbitMQConstants;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitMQConstants.getHost());
        connectionFactory.setPort(rabbitMQConstants.getPort());
        connectionFactory.setUsername(rabbitMQConstants.getUsername());
        connectionFactory.setPassword(rabbitMQConstants.getPassword());
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
