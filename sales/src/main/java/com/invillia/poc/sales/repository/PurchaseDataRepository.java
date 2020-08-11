package com.invillia.poc.sales.repository;

import com.invillia.poc.sales.domain.PurchaseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDataRepository extends JpaRepository<PurchaseData, Long> {
}
