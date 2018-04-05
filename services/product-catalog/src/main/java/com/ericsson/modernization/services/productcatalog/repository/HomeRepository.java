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
					"					where po.isDeleted = 0 \r\n" +
			" group by psc.name,po.isSellable";

	@Query(/*nativeQuery=true,*/ value = Q_OFFERING_SALESCHANNELS)
	List<HomeChartsDataProp> getOfferingOfSalesChannels();


	String Q_OFFERING_CATEGORY =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), psc.name,   case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end )\r\n" + 
			"					from ProductOffering po\r\n" + 
			"					join po.category psc \r\n" + 
			"					where psc.id not in (select inCt.parentId from Category as inCt )\r\n" +
					"					and po.isDeleted = 0 \r\n" +
			"					 group by psc.name,po.isSellable";

	//	select ct.name, count(*), 'Label' = case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end
	//	from ProductOffering as po
	//	join Category as ct on  ct.id  = po.category_id where ct.id not in (select inCt.parentId from Category as inCt) group by ct.name,po.isSellable

	@Query(/*nativeQuery=true,*/ value = Q_OFFERING_CATEGORY)
	List<HomeChartsDataProp> getOfferingOfCategories();


	String Q_DAILY_OFFERINGS =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), cast(DAY(po.createUserDate) as string)+'/'+cast(MONTH(po.createUserDate) as string), '' )\r\n" +
					"from ProductOffering po \r\n" +
					"where po.createUserDate > current_date() - 7 "+
					"group by po.createUserDate,cast(DAY(po.createUserDate) as string)+'/'+cast(MONTH(po.createUserDate) as string)";
	@Query(/*nativeQuery=true,*/ value = Q_DAILY_OFFERINGS)
	List<HomeChartsDataProp> getLast7DayscreatedOfferings();


	String Q_OFFERINGS_PARENT_CATEGORIES =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), psc.name, '1' )\r\n" + 
			"					from ProductOffering po\r\n" + 
			"					join po.category psc \r\n" + 
			"					where psc.id in (select inCt.parentId from Category as inCt )\r\n" +
			"					and po.isDeleted = 0 \r\n" +
			"					group by psc.name,po.isSellable";
	//	select ct.name, count(*)
	//	from ProductOffering as po
	//	join Category as ct on  ct.id  = po.category_id where ct.id in (select inCt.parentId from Category as inCt) group by ct.name
	@Query(/*nativeQuery=true,*/ value = Q_OFFERINGS_PARENT_CATEGORIES)
	List<HomeChartsDataProp> getOfferingsCountOfCategories();




	String Q_OFFERINGS_SEGMENTS =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), psc.name, 'Segmets')\r\n" +
					"from ProductOffering po \r\n" +
					" join po.segments psc \r\n" +
					" where po.isDeleted = 0 \r\n" +
					" group by psc.name";
	
	@Query(/*nativeQuery=true,*/ value = Q_OFFERINGS_SEGMENTS)
	List<HomeChartsDataProp> getOfferingSegments();





	String Q_OFFERING_STATUS =
			"select new com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp( count(po), case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed' end, '' )\r\n" +
					"					from ProductOffering po\r\n" +
					"					where po.isDeleted = 0 \r\n" +
					"					 group by po.isSellable";

	//	select ct.name, count(*), 'Label' = case when po.isSellable = 0 then 'Closed' when po.isSellable = 1 then 'Available' else 'Closed'  end
	//	from ProductOffering as po
	//	join Category as ct on  ct.id  = po.category_id where ct.id not in (select inCt.parentId from Category as inCt) group by ct.name,po.isSellable

	@Query(/*nativeQuery=true,*/ value = Q_OFFERING_STATUS)
	List<HomeChartsDataProp> getOfferingsStatus();





}