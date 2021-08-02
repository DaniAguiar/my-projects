package com.rabbitmq.teste.publisher;

import com.rabbitmq.teste.config.MessagingConfig;
import com.rabbitmq.teste.model.Order;
import com.rabbitmq.teste.model.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{name}")
    public String bookOrder(@RequestBody Order order, @PathVariable String name) {
        order.setId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order,"PROGRESS", "ordem processada com sucesso no restaurante: " + name);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Boa Jovem!";
    }
}
