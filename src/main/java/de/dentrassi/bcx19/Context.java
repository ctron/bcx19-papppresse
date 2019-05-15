package de.dentrassi.bcx19;

import org.apache.camel.component.amqp.AMQPConnectionDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {

    private @Value("${amqp.uri}") String amqpUri;
    private @Value("${amqp.username}") String amqpUsername;
    private @Value("${amqp.password}") String amqpPassword;

    @Bean
    public AMQPConnectionDetails amqpConnection() {
        return new AMQPConnectionDetails(this.amqpUri, this.amqpUsername, this.amqpPassword);
    }
}