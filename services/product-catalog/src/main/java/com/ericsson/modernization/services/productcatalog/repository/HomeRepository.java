package com.ericsson.modernization.services.productcatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp;
import com.ericsson.modernization.services.productcatalog.model.Document;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;

@Repository
public interface HomeRepository extends JpaRepository<ProductOffering, Integer> {

    String Q_OFFERING_SALESCHANNELS =
    		"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp(  count(po), psc.name, case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end )\r\n" +
			"from ProductOffering po \r\n" +
			"join po.salesChannels psc \r\n" +
			" group by psc.name,po.isSellable";

	@Query(/*nativeQuery=true,*/ value = Q_OFFERING_SALESCHANNELS)
	List<HomeChartsDataProp> getOfferingOfSalesChannels();


	String Q_OFFERING_CATEGORY =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), psc.name,   case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end )\r\n" +
					"from ProductOffering po \r\n" +
					"join po.salesChannels psc \r\n" +
					" group by psc.name,po.isSellable";
	@Query(/*nativeQuery=true,*/ value = Q_OFFERING_CATEGORY)
	List<HomeChartsDataProp> getOfferingOfCategories();


	String Q_DAILY_OFFERINGS =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), psc.name,   case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end )\r\n" +
					"from ProductOffering po \r\n" +
					"join po.salesChannels psc \r\n" +
					" group by psc.name,po.isSellable";
	@Query(/*nativeQuery=true,*/ value = Q_DAILY_OFFERINGS)
	List<HomeChartsDataProp> getLast7DayscreatedOfferings();


	String Q_OFFERINGS_PARENT_CATEGORIES =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), psc.name,   case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end )\r\n" +
					"from ProductOffering po \r\n" +
					"join po.salesChannels psc \r\n" +
					" group by psc.name,po.isSellable";
	@Query(/*nativeQuery=true,*/ value = Q_OFFERINGS_PARENT_CATEGORIES)
	List<HomeChartsDataProp> getOfferingsCountOfCategories();




	String Q_OFFERINGS_SEGMENTS =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), psc.name, 'Segmets')\r\n" +
					"from ProductOffering po \r\n" +
					"join po.segments psc \r\n" +
					" group by psc.name";
	@Query(/*nativeQuery=true,*/ value = Q_OFFERINGS_SEGMENTS)
	List<HomeChartsDataProp> getOfferingSegments();



}