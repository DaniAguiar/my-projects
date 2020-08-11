package com.invillia.poc.notegenerator.service.impl;

import com.invillia.poc.notegenerator.domain.PurchaseProcessor;
import com.invillia.poc.notegenerator.repository.PurchaseProcessorRepository;
import com.invillia.poc.notegenerator.service.PurchaseProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseProcessorServiceImpl implements PurchaseProcessorService {

    private final PurchaseProcessorRepository purchaseProcessorRepository;

    @Override
    public void save(PurchaseProcessor purchaseProcessor) {
        purchaseProcessorRepository.save(purchaseProcessor);
    }

    @Override
    public List<PurchaseProcessor> findAllPurchases() {
        return purchaseProcessorRepository.findAll();
    }
}
