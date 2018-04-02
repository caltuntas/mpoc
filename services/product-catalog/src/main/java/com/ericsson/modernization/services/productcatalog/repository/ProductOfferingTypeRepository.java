package com.ericsson.modernization.services.productcatalog.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOfferingType;

@Repository
public interface ProductOfferingTypeRepository extends JpaRepository<ProductOfferingType, Integer> {
	ProductOfferingType findByIdAndIsDeletedIsFalse(int id);
    List<ProductOfferingType> findAllByIsDeletedIsFalse();
}
