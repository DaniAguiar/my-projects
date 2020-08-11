package com.invillia.poc.notegenerator.mapper;

import com.invillia.poc.notegenerator.domain.PurchaseData;
import com.invillia.poc.notegenerator.domain.PurchaseProcessor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseProcessorMapper {

    PurchaseProcessor purchaseDataToPurchaseProcessor(PurchaseData purchaseData);
}
