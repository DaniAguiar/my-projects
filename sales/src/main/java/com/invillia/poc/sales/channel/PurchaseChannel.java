package com.invillia.poc.sales.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PurchaseChannel {

    String PURCHASE_CREATED_OUTPUT = "purchase-created-output";

    @Output(PURCHASE_CREATED_OUTPUT)
    MessageChannel outputPurchaseCreated();
}
