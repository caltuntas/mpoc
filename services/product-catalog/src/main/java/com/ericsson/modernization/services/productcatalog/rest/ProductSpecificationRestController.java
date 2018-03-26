package com.ericsson.modernization.services.productcatalog.rest;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.ProductSpecificationAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductSpecification.Request.ProductSpecificationCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productspec")
public class ProductSpecificationRestController {
    @Autowired
    private ProductSpecificationAppService productSpecificationAppService;

    @RequestMapping(value = "/createoffering", method = RequestMethod.POST)
    public ResponseEntity<String> createOffering(@RequestBody ProductSpecificationCreateRequest createRequest) {
        productSpecificationAppService.create(createRequest);
        return new ResponseEntity<>("created succesfully", HttpStatus.OK);
    }

    //@RequestMapping(value = "/getallofferings", method = RequestMethod.GET)
    //public ResponseEntity<List<ProductOffering>> getAllOfferings(){
      //  return new ResponseEntity<List<ProductOffering>>( productOfferingAppService.findAll(), HttpStatus.OK);
    //}
}
