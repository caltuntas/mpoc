package com.ericsson.modernization.services.productcatalog.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOfferingRelation;
import com.ericsson.modernization.services.productcatalog.model.ProductOfferingType;

@Repository
public interface ProductOfferingRelationRepository extends JpaRepository<ProductOfferingRelation, Integer> {
	ProductOfferingRelation findByIdAndIsDeletedIsFalse(int id);
    List<ProductOfferingRelation> findAllByIsDeletedIsFalse();
}
