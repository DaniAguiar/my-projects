package com.invillia.poc.notegenerator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseData {

    private Long id;
    private Long idProduct;
    private Long idAdditionalProduct;
    private Double price;
    private String createdAt;
    private String updatedAt;
}
