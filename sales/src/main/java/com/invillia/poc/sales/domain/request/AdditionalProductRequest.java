package com.invillia.poc.sales.domain.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdditionalProductRequest {

    @NotNull(message = "Name can't be null")
    private String name;

    @NotNull(message = "Price can't be null")
    private Double price;

    @NotNull(message = "Description can't be null")
    private String description;

    @NotNull(message = "Product can't be null")
    private Long idProduct;
}
