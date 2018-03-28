package com.ericsson.modernization.services.productcatalog.applicationservice.home;

import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeAppService {

    //@Autowired

    public String getHelloHome(){
        return "Hello Home Api";
    }

    public List<HomeChartsData> getChartsData(){

        List<HomeChartsData> list = new ArrayList<HomeChartsData>();

        int[] a1= {48,92};
        String labels[] = {"EBN", "CBN"};
        String name = "dataOfferingsSegment";

        list.add(new HomeChartsData(name,a1,labels,labels));

        return list;
    }

}




