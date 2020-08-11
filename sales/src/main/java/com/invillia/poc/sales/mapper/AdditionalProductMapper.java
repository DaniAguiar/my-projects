package com.invillia.poc.sales.mapper;

import com.invillia.poc.sales.domain.AdditionalProduct;
import com.invillia.poc.sales.domain.request.AdditionalProductRequest;
import com.invillia.poc.sales.domain.response.AdditionalProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdditionalProductMapper {

    @Mappings({
            @Mapping(source = "additionalProductRequest.name", target = "name"),
            @Mapping(source = "additionalProductRequest.price", target = "price"),
            @Mapping(source = "additionalProductRequest.description", target = "description"),
            @Mapping(source = "additionalProductRequest.idProduct", target = "product.id"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    AdditionalProduct additionalProductRequestToAdditionalProduct(AdditionalProductRequest additionalProductRequest);

    List<AdditionalProductResponse> additionalProductToAdditionalProductResponse(List<AdditionalProduct> additionalProducts);

    AdditionalProductResponse additionalProductToAdditionalProductResponse(AdditionalProduct additionalProduct);

    @Mappings({
            @Mapping(source = "additionalProductRequest.name", target = "name"),
            @Mapping(source = "additionalProductRequest.price", target = "price"),
            @Mapping(source = "additionalProductRequest.description", target = "description"),
            @Mapping(source = "additionalProductRequest.idProduct", target = "product.id")
    })
    AdditionalProduct updateAdditionalProductByAdditionalProductRequest(AdditionalProduct additionalProduct, AdditionalProductRequest additionalProductRequest);
}
