package com.ericsson.modernization.services.productcatalog.rest;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOfferingAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.request.ProductOfferingCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productoffering")
public class ProductOfferingRestController {
    @Autowired
    private ProductOfferingAppService productOfferingAppService;

    @RequestMapping(value = "/createoffering", method = RequestMethod.POST)
    public ResponseEntity<String> createOffering(@RequestBody ProductOfferingCreateRequest createRequest) {
        productOfferingAppService.create(createRequest);
        return new ResponseEntity<>("created succesfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/getallofferings", method = RequestMethod.GET)
    public ResponseEntity<List<ProductOffering>> getAllOfferings() {
        return new ResponseEntity<List<ProductOffering>>(productOfferingAppService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteOffering/{offeringId}", method = RequestMethod.GET)
    public ResponseEntity<String> deleteOffering(@PathVariable int offeringId) {
        productOfferingAppService.delete(offeringId);
        return new ResponseEntity<>("offering deleted", HttpStatus.OK);
    }
}
