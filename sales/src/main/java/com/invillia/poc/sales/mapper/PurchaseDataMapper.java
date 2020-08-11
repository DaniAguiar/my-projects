package com.invillia.poc.sales.mapper;

import com.invillia.poc.sales.domain.PurchaseData;
import com.invillia.poc.sales.domain.request.PurchaseDataRequest;
import com.invillia.poc.sales.domain.response.PurchaseDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseDataMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "price", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    PurchaseData purchaseDataRequestToPurchase(PurchaseDataRequest purchaseDataRequest);

    PurchaseDataResponse purchaseDataToPurchaseDataResponse(PurchaseData purchaseData);
}
