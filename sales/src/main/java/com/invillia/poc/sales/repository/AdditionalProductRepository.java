package com.invillia.poc.sales.repository;

import com.invillia.poc.sales.domain.AdditionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalProductRepository extends JpaRepository<AdditionalProduct, Long> {
    List<AdditionalProduct> findAdditionalProductsByProductId(Long id);
}
