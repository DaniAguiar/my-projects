package com.invillia.poc.notegenerator.controller;

import com.invillia.poc.notegenerator.domain.PurchaseProcessor;
import com.invillia.poc.notegenerator.service.PurchaseProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-processor")
@RequiredArgsConstructor
public class PurchaseProcessorController {

    private final PurchaseProcessorService purchaseProcessorService;

    @GetMapping
    public List<PurchaseProcessor> findAllPurchases(){
        return purchaseProcessorService.findAllPurchases();
    }
}
