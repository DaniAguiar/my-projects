package com.invillia.poc.sales.domain.response;

import lombok.Data;

@Data
public class PurchaseDataResponse {
    private Long id;
    private Long idProduct;
    private Long idAdditionalProduct;
    private Double price;
    private String createdAt;
    private String updatedAt;
}
