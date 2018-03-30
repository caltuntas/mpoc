package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.DiscountProdOfferPriceAlteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountProdOfferPriceAlterationRepository extends JpaRepository<DiscountProdOfferPriceAlteration, Integer> {
}
