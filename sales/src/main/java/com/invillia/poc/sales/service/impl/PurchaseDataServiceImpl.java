package com.invillia.poc.sales.service.impl;

import com.invillia.poc.sales.domain.PurchaseData;
import com.invillia.poc.sales.domain.request.PurchaseDataRequest;
import com.invillia.poc.sales.domain.response.PurchaseDataResponse;
import com.invillia.poc.sales.exception.ResourceNotFoundException;
import com.invillia.poc.sales.mapper.PurchaseDataMapper;
import com.invillia.poc.sales.repository.AdditionalProductRepository;
import com.invillia.poc.sales.repository.ProductRepository;
import com.invillia.poc.sales.repository.PurchaseDataRepository;
import com.invillia.poc.sales.service.PurchaseDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Properties;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class PurchaseDataServiceImpl implements PurchaseDataService {

    private final PurchaseDataRepository purchaseDataRepository;

    private final PurchaseDataMapper purchaseDataMapper;

    private final ProductRepository productRepository;

    private final AdditionalProductRepository additionalProductRepository;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public PurchaseDataResponse purchase(PurchaseDataRequest purchaseDataRequest) {
        final var purchaseData = purchaseDataMapper.purchaseDataRequestToPurchase(purchaseDataRequest);

        purchaseData.setPrice(calculateTotalValue(purchaseData.getIdProduct(), purchaseData.getIdAdditionalProduct()));

        purchaseDataRepository.save(purchaseData);

        publishPurchaseCreated(purchaseData);

        return purchaseDataMapper.purchaseDataToPurchaseDataResponse(purchaseData);
    }

    public void publishPurchaseCreated(PurchaseData purchaseData){
        log.info("M=publishPurchaseCreated, iniciando serialização, purchase={}", purchaseData);

        String topicName = "purchase-created";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.invillia.poc.sales.serializer.PurchaseDataSerializer");
        props.setProperty("request.timeout.ms", "1000");
        Producer<String, PurchaseData> producer = new KafkaProducer <>(props);

        producer.send(new ProducerRecord<>(topicName, "NOTE", purchaseData));

        log.info("M=publishPurchaseCreated, evento de compra enviado ao kafka, purchase={}", purchaseData);
        producer.close();
    }

    private Double calculateTotalValue(Long idProduct, Long idAdditionalProduct) {

        final var productPrice = productRepository.findById(idProduct)
                .orElseThrow(ResourceNotFoundException::new)
                .getPrice();

        if(idAdditionalProduct == null){
            return productPrice;
        }
        else{
            final var additionalProductPrice = additionalProductRepository.findById(idAdditionalProduct)
                    .orElseThrow(ResourceNotFoundException::new)
                    .getPrice();

            return productPrice + additionalProductPrice;
        }
    }
}
