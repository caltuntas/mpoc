package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSpecificationRepository  extends JpaRepository<ProductOffering, Integer> {
    ProductSpecification findByIdAndIsDeletedIsFalse(int id);
    List<ProductSpecification> findAllByIsDeletedIsFalse();
}
