package com.invillia.poc.sales.mapper;

import com.invillia.poc.sales.domain.Product;
import com.invillia.poc.sales.domain.request.ProductRequest;
import com.invillia.poc.sales.domain.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "additionalProducts", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Product productRequestToProduct(final ProductRequest productRequest);

    List<ProductResponse> productToProductResponse(List<Product> products);

    ProductResponse productToProductResponse(Product product);

    @Mappings({
            @Mapping(source = "productRequest.name", target = "name"),
            @Mapping(source = "productRequest.price", target = "price"),
            @Mapping(source = "productRequest.description", target = "description")
    })
    Product updateProductByProductRequest(Product product, ProductRequest productRequest);
}
