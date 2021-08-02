package com.graphqlandrabbitmq.invoice.consumer;

import com.graphqlandrabbitmq.invoice.config.MessagingConfig;
import com.graphqlandrabbitmq.invoice.model.DemandStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(DemandStatus demandStatus) {
        System.out.println("Mensagem recebida da fila: " + demandStatus);
    }
}
