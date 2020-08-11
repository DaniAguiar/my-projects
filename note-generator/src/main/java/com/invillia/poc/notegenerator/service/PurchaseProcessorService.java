package com.invillia.poc.notegenerator.service;

import com.invillia.poc.notegenerator.domain.PurchaseProcessor;

import java.util.List;

public interface PurchaseProcessorService {
    void save(PurchaseProcessor purchaseProcessor);

    List<PurchaseProcessor> findAllPurchases();
}
