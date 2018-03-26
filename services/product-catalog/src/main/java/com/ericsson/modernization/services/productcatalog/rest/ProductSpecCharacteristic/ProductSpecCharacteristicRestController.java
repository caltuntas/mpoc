package com.ericsson.modernization.services.productcatalog.rest.ProductSpecCharacteristic;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecCharacteristic.ProductSpecCharacteristicAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecCharacteristic.Request.ProductSpecCharacteristicCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecCharacteristic.Response.ProductSpecCharacteristicServiceResponse;
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

    @RequestMapping(value = "/createproductspeccharacteristic", method = RequestMethod.POST)
    public ResponseEntity<ProductSpecCharacteristicServiceResponse> createProductSpecCharacteristic(@RequestBody ProductSpecCharacteristicCreateRequest productSpecCharacteristicCreateRequest) {
        ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicAppService.create(productSpecCharacteristicCreateRequest);
        ProductSpecCharacteristicServiceResponse response = new ProductSpecCharacteristicServiceResponse();
        response.setMessage("A product specification characteristic with id : " + productSpecCharacteristic.getId() + " is created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallcharacteristics", method = RequestMethod.GET)
    public ResponseEntity<List<ProductSpecCharacteristic>> getallcharacteristics(){
        return new ResponseEntity<List<ProductSpecCharacteristic>>(productSpecCharacteristicAppService.findAll(), HttpStatus.OK);
    }
}
