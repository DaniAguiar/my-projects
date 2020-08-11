package com.invillia.poc.notegenerator.listener;

import com.invillia.poc.notegenerator.domain.PurchaseData;
import com.invillia.poc.notegenerator.mapper.PurchaseProcessorMapper;
import com.invillia.poc.notegenerator.service.PurchaseProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Component
@EnableKafka
@RequiredArgsConstructor
public class PurchaseListener {

    private final PurchaseProcessorService purchaseProcessorService;

    private final PurchaseProcessorMapper purchaseProcessorMapper;

    private PurchaseData purchaseData;

    @KafkaListener(topics = "purchase-created", groupId = "purchase.topic")
    void consume(){

        String topicName = "purchase-created";
        String groupName = "purchase.topic";

        Properties props = new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("group.id", groupName);
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("max.poll.interval.ms", "2000");
        props.setProperty("max.poll.records", "100");
//        props.setProperty("metadata.max.age.ms", "3000");
//        props.setProperty("heartbeat.interval.ms", "100");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "com.invillia.poc.notegenerator.deserializer.PurchaseDataDeserializer");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, PurchaseData> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));

        while (true){
            ConsumerRecords<String, PurchaseData> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, PurchaseData> record : records){
                log.info("M=consume, consumindo evento do kafka");

                purchaseData = PurchaseData.builder()
                        .id(record.value().getId())
                        .idProduct(record.value().getIdProduct())
                        .idAdditionalProduct(record.value().getIdAdditionalProduct())
                        .price(record.value().getPrice())
                        .createdAt(record.value().getCreatedAt())
                        .updatedAt(record.value().getUpdatedAt())
                        .build();

                final var topicPartition = new TopicPartition(record.topic(), record.partition());
                final var offsetAndMetadata = new OffsetAndMetadata(record.offset() + 1);
                consumer.commitSync(Map.of(topicPartition, offsetAndMetadata));

                final var purchaseProcessor = purchaseProcessorMapper.purchaseDataToPurchaseProcessor(purchaseData);

                purchaseProcessorService.save(purchaseProcessor);

                log.info("M=consume, salvando evento do kafka purchaseProcessor={}", purchaseProcessor);
            }
        }
    }
}
