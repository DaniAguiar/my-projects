package com.invillia.poc.sales.service;

import com.invillia.poc.sales.domain.request.PurchaseDataRequest;
import com.invillia.poc.sales.domain.response.PurchaseDataResponse;

public interface PurchaseDataService {

    PurchaseDataResponse purchase(PurchaseDataRequest purchaseDataRequest);
}
