package com.ericsson.modernization.services.productcatalog.applicationservice.home;

import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsData;
import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp;
import com.ericsson.modernization.services.productcatalog.repository.HomeRepository;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Persistence;

@Service
public class HomeAppService {

	@Autowired
    private HomeRepository homeRepository;

    public String getHelloHome(){
        return "Hello Home Api";
    }

    public List<HomeChartsData> getChartsData(){

        List<HomeChartsData> list = new ArrayList<HomeChartsData>();

        int[] a1= {48,92};
        String labels[] = {"EBN", "CBN"};
        String name = "dataOfferingsSegment";

        //list.add(new HomeChartsData(name,a1,labels,labels));

        return list;
    }
    
    public List<HomeChartsDataProp> getOfferingOfSalesChannels() {
    	List<HomeChartsDataProp> o = homeRepository.getOfferingOfSalesChannels();
    	return o;
    }

    public List<HomeChartsDataProp> getLast7DayscreatedOfferings() {
    	List<HomeChartsDataProp> o = homeRepository.getLast7DayscreatedOfferings();
    	return o;
    }

    public List<HomeChartsDataProp> getOfferingOfCategories() {
        List<HomeChartsDataProp> o = homeRepository.getOfferingOfCategories();
        return o;
    }



    public List<HomeChartsDataProp> getOfferingsCountOfCategories() {
        List<HomeChartsDataProp> o = homeRepository.getOfferingsCountOfCategories();
        return o;
    }

    public List<HomeChartsDataProp> getOfferingSegments() {
        List<HomeChartsDataProp> o = homeRepository.getOfferingSegments();
        return o;
    }



    public List<HomeChartsDataProp> getOfferingsStatus() {
        List<HomeChartsDataProp> o = homeRepository.getOfferingsStatus();
        return o;
    }

}




