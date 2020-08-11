package com.invillia.poc.sales.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseDataRequest {

    @NotNull(message = "Product can't be null")
    private Long idProduct;

    private Long idAdditionalProduct;
}
