package com.invillia.poc.notegenerator.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PurchaseChannel {

    String PURCHASE_CREATED_INPUT = "purchase-created-input";

    @Output(PURCHASE_CREATED_INPUT)
    MessageChannel inputPurchaseCreated();
}
