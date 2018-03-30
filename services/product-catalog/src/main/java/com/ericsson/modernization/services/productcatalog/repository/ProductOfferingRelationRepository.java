package com.ericsson.modernization.services.productcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOfferingRelation;

@Repository
public interface ProductOfferingRelationRepository extends JpaRepository<ProductOfferingRelation, Integer> {
}
