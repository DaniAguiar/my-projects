package com.invillia.poc.sales.domain.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotNull(message = "Name can't be null")
    private String name;

    @NotNull(message = "Price can't be null")
    private Double price;

    @NotNull(message = "Description can't be null")
    private String description;
}
