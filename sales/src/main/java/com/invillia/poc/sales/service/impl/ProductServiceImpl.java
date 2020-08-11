package com.invillia.poc.sales.service.impl;

import com.invillia.poc.sales.domain.AdditionalProduct;
import com.invillia.poc.sales.domain.Product;
import com.invillia.poc.sales.domain.request.ProductRequest;
import com.invillia.poc.sales.domain.response.AdditionalProductResponse;
import com.invillia.poc.sales.domain.response.ProductResponse;
import com.invillia.poc.sales.exception.ResourceNotFoundException;
import com.invillia.poc.sales.mapper.AdditionalProductMapper;
import com.invillia.poc.sales.mapper.ProductMapper;
import com.invillia.poc.sales.repository.AdditionalProductRepository;
import com.invillia.poc.sales.repository.ProductRepository;
import com.invillia.poc.sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final AdditionalProductMapper additionalProductMapper;

    private final AdditionalProductRepository additionalProductRepository;

    @Override
    public Product create(final ProductRequest productRequest){
        final var product = productMapper.productRequestToProduct(productRequest);

        return productRepository.save(product);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        final List<Product> products = productRepository.findAll();

        return productMapper.productToProductResponse(products);
    }

    @Override
    public ProductResponse findProductById(final Long id) {
        final var product = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return productMapper.productToProductResponse(product);
    }

    @Override
    public void update(final Long id, final ProductRequest productRequest) {
        final var product = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        productRepository.save(productMapper.updateProductByProductRequest(product, productRequest));
    }

    @Override
    public List<AdditionalProductResponse> findAdditionalProductsByProductId(final Long id) {
        final var additionalProducts = additionalProductRepository.findAdditionalProductsByProductId(id);

        return additionalProductMapper.additionalProductToAdditionalProductResponse(additionalProducts);
    }

    @Override
    public void delete(final Long id) {
        final Product product = productRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        productRepository.delete(product);
    }
}