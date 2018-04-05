package com.ericsson.modernization.services.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOffering;

@Repository
public interface ProductOfferingRepository
		extends JpaRepository<ProductOffering, Integer> {
	ProductOffering findByIdAndIsDeletedIsFalse(int id);

	List<ProductOffering> findAllByIsDeletedIsFalse();

	List<ProductOffering> findAllByProductOfferingTypeIdAndIsDeletedIsFalse(int productOfferingTypeId);

}
