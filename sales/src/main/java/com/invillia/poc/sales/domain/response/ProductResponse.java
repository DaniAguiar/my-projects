package com.invillia.poc.sales.domain.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String createdAt;
    private String updatedAt;
}
