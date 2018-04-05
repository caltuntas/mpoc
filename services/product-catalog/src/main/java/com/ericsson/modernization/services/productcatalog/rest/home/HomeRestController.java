package com.ericsson.modernization.services.productcatalog.rest.home;


import com.ericsson.modernization.services.productcatalog.applicationservice.home.HomeAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsData;
import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsDataProp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/home")
public class HomeRestController {


    @Autowired
    private HomeAppService homeAppService;


    @RequestMapping(value = "/getHome", method = RequestMethod.GET)
    public ResponseEntity<String> getHome(){
        return new ResponseEntity<String>( homeAppService.getHelloHome(), HttpStatus.OK);
    }


    @RequestMapping(value = "/getChartsData", method = RequestMethod.GET)
    public ResponseEntity<List<HomeChartsData>> getChartsData(){
        List<HomeChartsData> homeChartsDataRaw = homeAppService.getChartsData();
        List<HomeChartsData> homeChartsDataList = new ArrayList<>();
        for (HomeChartsData productOffering : homeChartsDataRaw) {
            homeChartsDataList.add(new HomeChartsData
                    (
                            productOffering.getName(),
                            productOffering.getData(),
                            productOffering.getDatalabel(),
                            productOffering.getLabels()));
        }
        return new ResponseEntity<>(homeChartsDataList, HttpStatus.OK);

    }

    @RequestMapping(value = "/getOfferingSalesChannels", method = RequestMethod.GET)
    public ResponseEntity<List<HomeChartsData>> getOfferingOfSalesChannels(){
        List<HomeChartsDataProp> homeChartsDataRaw = homeAppService.getOfferingOfSalesChannels();
        List<HomeChartsData> homeChartsDataList = ConvertDoubleLayerData(homeChartsDataRaw, "OfferingSalesChannels");
        return new ResponseEntity<List<HomeChartsData>>( homeChartsDataList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getLast7DayscreatedOfferings", method = RequestMethod.GET)
    public ResponseEntity<List<HomeChartsData>> getLast7DayscreatedOfferings(){
        List<HomeChartsDataProp> homeChartsDataRaw = homeAppService.getLast7DayscreatedOfferings();
        List<HomeChartsData> homeChartsDataList = ConvertDoubleLayerData(homeChartsDataRaw,"GetLast7DayscreatedOfferings");
        return new ResponseEntity<List<HomeChartsData>>( homeChartsDataList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getOfferingsCountOfCategories", method = RequestMethod.GET)
    public ResponseEntity<List<HomeChartsData>> getOfferingsCountOfCategories(){
        List<HomeChartsDataProp> homeChartsDataRaw = homeAppService.getOfferingsCountOfCategories();
        List<HomeChartsData> homeChartsDataList = ConvertDoubleLayerData(homeChartsDataRaw,"OfferingsCountOfCategories");
        return new ResponseEntity<List<HomeChartsData>>( homeChartsDataList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getOfferingOfCategories", method = RequestMethod.GET)
    public ResponseEntity<List<HomeChartsData>> getOfferingOfCategories(){
        List<HomeChartsDataProp> homeChartsDataRaw = homeAppService.getOfferingOfCategories();
        List<HomeChartsData> homeChartsDataList = ConvertDoubleLayerData(homeChartsDataRaw,"OfferingOfCategories");
        return new ResponseEntity<List<HomeChartsData>>( homeChartsDataList, HttpStatus.OK);
    }


    @RequestMapping(value = "/getOfferingSegments", method = RequestMethod.GET)
    public ResponseEntity<List<HomeChartsData>> getOfferingSegments(){
        List<HomeChartsDataProp> homeChartsDataRaw = homeAppService.getOfferingSegments();
        List<HomeChartsData> homeChartsDataList = ConvertDoubleLayerData(homeChartsDataRaw, "OfferingSegments");
        return new ResponseEntity<List<HomeChartsData>>( homeChartsDataList, HttpStatus.OK);
    }





    @RequestMapping(value = "/getOfferingsStatus", method = RequestMethod.GET)
    public ResponseEntity<List<HomeChartsData>> getOfferingsStatus(){
        List<HomeChartsDataProp> homeChartsDataRaw = homeAppService.getOfferingsStatus();
        List<HomeChartsData> homeChartsDataList = ConvertDoubleLayerData(homeChartsDataRaw, "OfferingsStatus");
        return new ResponseEntity<List<HomeChartsData>>( homeChartsDataList, HttpStatus.OK);
    }


    private List<HomeChartsData> ConvertDoubleLayerData( List<HomeChartsDataProp>  homeChartsDataRaw, String name){

        List<String> dataLabels = new ArrayList<String>();
        for (HomeChartsDataProp item : homeChartsDataRaw) {
            if(!dataLabels.contains(item.getDataLabel())){
                dataLabels.add(item.getDataLabel());
            }
        }
        List<String> labels = new ArrayList<String>();
        for (HomeChartsDataProp item : homeChartsDataRaw) {
            if(!labels.contains(item.getLabel())){
                labels.add(item.getLabel());
            }
        }
        ArrayList<String> finalDataLabels = new ArrayList<String>();
        ArrayList<String> finalLabels = new ArrayList<String>();
        ArrayList<ArrayList<Long>> finaldata = new ArrayList<ArrayList<Long>>();
        boolean isNotFirst = false;
        for(String dataLabel : dataLabels){
            ArrayList<Long> currentdata = new ArrayList<Long>();
            //List<HomeChartsDataProp> currentHomeChartsDataRaw = homeChartsDataRaw.stream().filter((data) -> data.getDataLabel() == dataLabel).collect(Collectors.toList());
            List<HomeChartsDataProp> currentHomeChartsDataRaw = new ArrayList();
            for(HomeChartsDataProp filteredListItem : homeChartsDataRaw) {
                if(filteredListItem.getDataLabel().toString().equals(dataLabel.toString())){
                    currentHomeChartsDataRaw.add(filteredListItem);
                }
            }
            for(HomeChartsDataProp filteredListItem : currentHomeChartsDataRaw){
                if(!finalLabels.contains(filteredListItem.getLabel())){
                    currentdata.add(filteredListItem.getData());
                    finalLabels.add(filteredListItem.getLabel());
                    if(isNotFirst){
                        for(ArrayList<Long> updatedData : finaldata){
                            updatedData.add(0L);
                        }
                    }
                }
                else{
                    if(finalLabels.size() != currentdata.size()){
                        currentdata.add(filteredListItem.getData());
                    }else{
                        for(int x = 0; x < finalLabels.size(); x = x + 1) {
                            if(finalLabels.get(x).toString().equals(filteredListItem.getLabel().toString())){
                                Long count = currentdata.get(x);
                                currentdata.set(x,count+filteredListItem.getData());
                            }
                        }
                    }
                }
            }
            finalDataLabels.add(dataLabel);
            finaldata.add(currentdata);
            isNotFirst = true;
        }
        List<HomeChartsData> homeChartsDataList = new ArrayList<>();
        for(int x = 0; x < finalDataLabels.size(); x = x + 1) {
            homeChartsDataList.add(new HomeChartsData(
                    name,
                    finaldata.get(x),
                    finalDataLabels.get(x),
                    finalLabels));

        }
        return homeChartsDataList;
    }



}
