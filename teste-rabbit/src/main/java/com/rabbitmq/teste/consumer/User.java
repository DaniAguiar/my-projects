package com.rabbitmq.teste.consumer;

import com.rabbitmq.teste.config.MessagingConfig;
import com.rabbitmq.teste.model.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Mensagem recebida da queue: " + orderStatus);
    }
}
