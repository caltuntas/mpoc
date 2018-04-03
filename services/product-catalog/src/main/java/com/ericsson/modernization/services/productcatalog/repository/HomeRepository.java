package com.ericsson.modernization.services.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.ProductOfferingProp;
import com.ericsson.modernization.services.productcatalog.model.Document;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;

@Repository
public interface HomeRepository extends JpaRepository<ProductOffering, Integer> {

    String Q_OFFERING_SALESCHANNELS = /*"select sc.\"name\", count(*) \"count\",  case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end isSellable\r\n" + 
    		"from ProductOffering as po \r\n" + 
    		"join ProductOfferingSalesChannels psc on psc.productoffering_id = po.id\r\n" + 
    		"join SalesChannel as sc on  sc.id  = psc.saleschannel_id group by sc.\"name\",po.isSellable";
    */
    		
    		"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.ProductOfferingProp( psc.name, count(po),  case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end )\r\n" + 
			"from ProductOffering po \r\n" + 
			"join po.salesChannels psc \r\n" + 
			" group by psc.name,po.isSellable";

	@Query(/*nativeQuery=true,*/ value = Q_OFFERING_SALESCHANNELS)
		List<ProductOfferingProp> getOfferingSalesChannels();
	
	}