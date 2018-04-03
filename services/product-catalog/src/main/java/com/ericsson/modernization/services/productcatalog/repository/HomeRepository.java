package com.ericsson.modernization.services.productcatalog.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.*;

public interface HomeRepository extends JpaRepository<Object, Long> {

	String Q_OFFERING_SALESCHANNELS = "select sc.name, count(sc), \"Label\" = case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end\r\n" + 
			"from ProductOffering po \r\n" + 
			"join po.salesChannels psc \r\n" + 
			"join SalesChannel as sc group by sc.name,po.isSellable";

	@Query(Q_OFFERING_SALESCHANNELS)
		Collection<Object> getOfferingSalesChannels();
	}