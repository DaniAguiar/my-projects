package com.invillia.poc.sales.service;

import com.invillia.poc.sales.domain.AdditionalProduct;
import com.invillia.poc.sales.domain.Product;
import com.invillia.poc.sales.domain.request.ProductRequest;
import com.invillia.poc.sales.domain.response.AdditionalProductResponse;
import com.invillia.poc.sales.domain.response.ProductResponse;

import java.util.List;

public interface ProductService {

    Product create(ProductRequest productRequest);

    void delete(Long id);

    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(Long id);

    void update(Long id, ProductRequest productRequest);

    List<AdditionalProductResponse> findAdditionalProductsByProductId(Long id);
}
