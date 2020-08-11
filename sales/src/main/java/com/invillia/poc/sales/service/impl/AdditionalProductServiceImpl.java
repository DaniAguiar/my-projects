package com.invillia.poc.sales.service.impl;

import com.invillia.poc.sales.domain.AdditionalProduct;
import com.invillia.poc.sales.domain.request.AdditionalProductRequest;
import com.invillia.poc.sales.domain.response.AdditionalProductResponse;
import com.invillia.poc.sales.exception.ResourceNotFoundException;
import com.invillia.poc.sales.mapper.AdditionalProductMapper;
import com.invillia.poc.sales.repository.AdditionalProductRepository;
import com.invillia.poc.sales.repository.ProductRepository;
import com.invillia.poc.sales.service.AdditionalProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalProductServiceImpl implements AdditionalProductService {

    private final AdditionalProductRepository additionalProductRepository;

    private final AdditionalProductMapper additionalProductMapper;

    private final ProductRepository productRepository;

    @Override
    public AdditionalProduct create(final AdditionalProductRequest additionalProductRequest) {
        final var additionalProduct = additionalProductMapper.additionalProductRequestToAdditionalProduct(additionalProductRequest);

        additionalProduct.setProduct(productRepository.findById(additionalProduct.getProduct().getId()).get());

        return additionalProductRepository.save(additionalProduct);
    }

    @Override
    public List<AdditionalProductResponse> findAllAdditionalProducts() {
        final List<AdditionalProduct> additionalProducts = additionalProductRepository.findAll();

        return additionalProductMapper.additionalProductToAdditionalProductResponse(additionalProducts);
    }

    @Override
    public AdditionalProductResponse findAdditionalProductById(final Long id) {
        final var additionalProduct = additionalProductRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return additionalProductMapper.additionalProductToAdditionalProductResponse(additionalProduct);
    }

    @Override
    public void update(final Long id, final AdditionalProductRequest additionalProductRequest) {
        final var additionalProduct = additionalProductRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        additionalProductRepository.save(additionalProductMapper.updateAdditionalProductByAdditionalProductRequest(additionalProduct, additionalProductRequest));
    }

    @Override
    public void delete(final Long id) {
        final var additionalProduct = additionalProductRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        additionalProductRepository.delete(additionalProduct);
    }
}
