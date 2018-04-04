package com.ericsson.modernization.services.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOffering;

@Repository
public interface ProductOfferingRepository
		extends JpaRepository<ProductOffering, Integer>, ProductOfferingRepositoryCustom {
	ProductOffering findByIdAndIsDeletedIsFalse(int id);

	List<ProductOffering> findAllByIsDeletedIsFalse();

	List<ProductOffering> findAllByProductOfferingTypeIdAndIsDeletedIsFalse(int productOfferingTypeId);
/*
	ProductOffering findByIdAndClonnedProductOfferingIdAndIsDeletedIsFalse(Integer bundleProductOfferingId,
			Integer simpleOfferingId);
*//*
	@Query("select po from ProductOffering po\r\n" + 
			"where po.id IN (select por.relatedProductOfferingId from ProductOfferingRelation por where por.productOfferingId = :bundleProductOfferingId)\r\n" + 
			"AND po.productOfferingType_id = 3\r\n" + 
			"AND po.isDeleted = 0")
	List<ProductOffering> findByBundleAndSimpleRelation(@Param("bundleProductOfferingId") int bundleOfferingId);*/
}
