package com.ericsson.modernization.services.productcatalog.rest.home;


import com.ericsson.modernization.services.productcatalog.applicationservice.home.HomeAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.home.response.HomeChartsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;


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
                            productOffering.getDatalabels(),
                            productOffering.getLabels()));
        }
        return new ResponseEntity<>(homeChartsDataList, HttpStatus.OK);
        //return new ResponseEntity<List<HomeChartsData>>( homeAppService.getChartsData(), HttpStatus.OK);
        //yukardaki kod disable SerializationFeature.FAIL_ON_EMPTY_BEANS hatası aldı
    }

}
