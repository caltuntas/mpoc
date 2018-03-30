package com.ericsson.modernization.services.productcatalog.rest.productoffering;

import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.ProductOfferingAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request.ProductOfferingDetailModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.CommonServiceResponse;
import com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response.ProductOfferingListModel;
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
    public ResponseEntity<CommonServiceResponse> createOffering(@RequestBody ProductOfferingDetailModel createRequest) {
        ProductOffering productOffering = productOfferingAppService.create(createRequest);
        CommonServiceResponse response = new CommonServiceResponse();
        response.setMessage("An offfering with id : " + productOffering.getId() + " is created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateoffering", method = RequestMethod.POST)
    public ResponseEntity<CommonServiceResponse> updateOffering(@RequestBody ProductOfferingDetailModel editRequest) {
        productOfferingAppService.update(editRequest);
        CommonServiceResponse response = new CommonServiceResponse();
        response.setMessage("The offfering with id : " + editRequest.getId() + " is edited");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallofferings", method = RequestMethod.GET)
    public ResponseEntity<List<ProductOfferingListModel>> getAllOfferings() {
        return new ResponseEntity<>(productOfferingAppService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteoffering/{offeringId}", method = RequestMethod.GET)
    public ResponseEntity<CommonServiceResponse> deleteOffering(@PathVariable int offeringId) {
        productOfferingAppService.delete(offeringId);
        CommonServiceResponse response = new CommonServiceResponse();
        response.setMessage("The offfering with id : " + offeringId + " is deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getoffering/{offeringId}", method = RequestMethod.GET)
    public ResponseEntity<ProductOfferingDetailModel> getOffering(@PathVariable int offeringId) {
        ProductOfferingDetailModel detailModel = productOfferingAppService.findByIdForEditing(offeringId);
        return new ResponseEntity<>(detailModel, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/productOfferingTypeId={productOfferingTypeId}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductOfferingListModel>> getAllOfferingsByProductOfferingTypeId(@PathVariable int productOfferingTypeId) {
        return new ResponseEntity<>(productOfferingAppService.findAllByProductOfferingTypeId(productOfferingTypeId), HttpStatus.OK);
    }
}
