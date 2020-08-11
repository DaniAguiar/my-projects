package com.invillia.poc.sales.controller;

import com.invillia.poc.sales.domain.AdditionalProduct;
import com.invillia.poc.sales.domain.request.AdditionalProductRequest;
import com.invillia.poc.sales.domain.response.AdditionalProductResponse;
import com.invillia.poc.sales.service.AdditionalProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/additional-products")
@RequiredArgsConstructor
public class AdditionalProductController {

    private final AdditionalProductService additionalProductService;

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody final AdditionalProductRequest additionalProductRequest){
        final var additionalProduct = additionalProductService.create(additionalProductRequest);

        final var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(additionalProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(additionalProduct.getId());
    }

    @GetMapping
    public List<AdditionalProductResponse> findAllAdditionalProducts(){
        return additionalProductService.findAllAdditionalProducts();
    }

    @GetMapping("/{id}")
    public AdditionalProductResponse findAdditionalProductById(@PathVariable final Long id){
        return additionalProductService.findAdditionalProductById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable final Long id,
                             @Valid @RequestBody final AdditionalProductRequest additionalProductRequest){
        additionalProductService.update(id, additionalProductRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(final @PathVariable Long id){
        additionalProductService.delete(id);
        return ResponseEntity.ok().build();
    }
}
