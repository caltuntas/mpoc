package com.ericsson.modernization.services.productcatalog.repository;

import com.ericsson.modernization.services.productcatalog.model.OneTimeProdOfferPriceCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneTimeProdOfferPriceChargeRepository extends JpaRepository<OneTimeProdOfferPriceCharge, Integer> {
}
