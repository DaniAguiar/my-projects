package com.invillia.poc.notegenerator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class PurchaseProcessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idProduct;

    @Column
    private Long idAdditionalProduct;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;
}
