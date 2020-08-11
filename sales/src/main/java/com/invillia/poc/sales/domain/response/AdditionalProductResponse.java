package com.invillia.poc.sales.domain.response;

import lombok.Data;

@Data
public class AdditionalProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String createdAt;
    private String updatedAt;
    private ProductResponse product;
}
