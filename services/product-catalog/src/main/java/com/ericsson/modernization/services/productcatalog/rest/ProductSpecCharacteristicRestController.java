package com.ericsson.modernization.services.productcatalog.rest;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecCharacteristicAppService;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productspeccharacteristic")
public class ProductSpecCharacteristicRestController {
    @Autowired
    private ProductSpecCharacteristicAppService productSpecCharacteristicAppService;

    @RequestMapping(value = "/getallcharacteristics", method = RequestMethod.GET)
    public ResponseEntity<List<ProductSpecCharacteristic>> getallcharacteristics(){
        return new ResponseEntity<List<ProductSpecCharacteristic>>(productSpecCharacteristicAppService.findAll(), HttpStatus.OK);
    }
}
