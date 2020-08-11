package com.invillia.poc.sales.controller;

import com.invillia.poc.sales.domain.AdditionalProduct;
import com.invillia.poc.sales.domain.request.ProductRequest;
import com.invillia.poc.sales.domain.response.AdditionalProductResponse;
import com.invillia.poc.sales.domain.response.ProductResponse;
import com.invillia.poc.sales.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody final ProductRequest productRequest){
        final var product = productService.create(productRequest);

        final var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(product.getId());
    }

    @GetMapping
    public List<ProductResponse> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse findProductById(@PathVariable final Long id){
        return productService.findProductById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable final Long id,
                             @Valid @RequestBody final ProductRequest productRequest){
        productService.update(id, productRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(final @PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/additional-products")
    public List<AdditionalProductResponse> findAdditionalProductsByProductId(@PathVariable final Long id){
        return productService.findAdditionalProductsByProductId(id);
    }
}
