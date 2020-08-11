package com.invillia.poc.notegenerator.repository;

import com.invillia.poc.notegenerator.domain.PurchaseProcessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProcessorRepository extends JpaRepository<PurchaseProcessor, Long> {
}
