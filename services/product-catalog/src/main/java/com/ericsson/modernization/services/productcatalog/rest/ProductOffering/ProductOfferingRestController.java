package com.ericsson.modernization.services.productcatalog.rest.ProductOffering;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.ProductOfferingAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.Request.ProductOfferingCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.Response.ProductOfferingServiceResponse;
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
    public ResponseEntity<ProductOfferingServiceResponse> createOffering(@RequestBody ProductOfferingCreateRequest createRequest) {
        ProductOffering productOffering = productOfferingAppService.create(createRequest);
        ProductOfferingServiceResponse response = new ProductOfferingServiceResponse();
        response.setMessage("An offfering with id : " + productOffering.getId() + " is created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallofferings", method = RequestMethod.GET)
    public ResponseEntity<List<ProductOffering>> getAllOfferings() {
        return new ResponseEntity<List<ProductOffering>>(productOfferingAppService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteOffering/{offeringId}", method = RequestMethod.GET)
    public ResponseEntity<ProductOfferingServiceResponse> deleteOffering(@PathVariable int offeringId) {
        productOfferingAppService.delete(offeringId);
        ProductOfferingServiceResponse response = new ProductOfferingServiceResponse();
        response.setMessage("The offfering with id : " + offeringId + " is created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
