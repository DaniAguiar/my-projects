package com.invillia.poc.sales.service;

import com.invillia.poc.sales.domain.AdditionalProduct;
import com.invillia.poc.sales.domain.request.AdditionalProductRequest;
import com.invillia.poc.sales.domain.response.AdditionalProductResponse;

import java.util.List;

public interface AdditionalProductService {
    AdditionalProduct create(AdditionalProductRequest additionalProductRequest);

    List<AdditionalProductResponse> findAllAdditionalProducts();

    AdditionalProductResponse findAdditionalProductById(Long id);

    void update(Long id, AdditionalProductRequest additionalProductRequest);

    void delete(Long id);
}
