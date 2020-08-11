package com.invillia.poc.sales.controller;

import com.invillia.poc.sales.domain.request.PurchaseDataRequest;
import com.invillia.poc.sales.domain.response.PurchaseDataResponse;
import com.invillia.poc.sales.service.PurchaseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseDataController {

    private final PurchaseDataService purchaseDataService;

    @PostMapping
    public PurchaseDataResponse purchase(@Valid @RequestBody final PurchaseDataRequest purchaseDataRequest){
        return purchaseDataService.purchase(purchaseDataRequest);
    }
}
