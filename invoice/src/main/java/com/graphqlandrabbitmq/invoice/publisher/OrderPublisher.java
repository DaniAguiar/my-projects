package com.graphqlandrabbitmq.invoice.publisher;

import com.graphqlandrabbitmq.invoice.config.MessagingConfig;
import com.graphqlandrabbitmq.invoice.model.Demand;
import com.graphqlandrabbitmq.invoice.model.DemandStatus;
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
    public String bookOrder(@RequestBody Demand demand, @PathVariable String name) {
        DemandStatus demandStatus = new DemandStatus(demand,"PROGRESS", "ordem processada com sucesso no restaurante: " + name);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, demandStatus);
        return "Boa Jovem!";
    }
}
