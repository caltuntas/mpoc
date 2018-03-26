package com.ericsson.modernization.services.productcatalog.rest.ProductOffering;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.ProductOfferingAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.Request.ProductOfferingCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.Response.ProductOfferingListResponse;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.Response.ProductOfferingServiceResponse;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<ProductOfferingListResponse>> getAllOfferings() {
        List<ProductOffering> productOfferings = productOfferingAppService.findAll();
        List<ProductOfferingListResponse> productOfferingResponseList = new ArrayList<>();

        for (ProductOffering productOffering : productOfferings) {
            ProductOfferingListResponse response =
                    new ProductOfferingListResponse
                            (
                                    productOffering.getId(),
                                    productOffering.getName(),
                                    productOffering.getValidFor() != null ? productOffering.getValidFor().getValidForStartDate() : null,
                                    productOffering.getValidFor() != null ? productOffering.getValidFor().getValidForEndDate() : null,
                                    productOffering.getDescription(),
                                    productOffering.getExternalId(),
                                    productOffering.getWarrantyPeriod() != null ? productOffering.getWarrantyPeriod().getPeriodValue() : 0,
                                    productOffering.getWarrantyPeriod() != null ? productOffering.getWarrantyPeriod().getPeriodUnit() : 0,
                                    productOffering.getReturnPeriod() != null ? productOffering.getReturnPeriod().getPeriodValue() : 0,
                                    productOffering.getReturnPeriod() != null ? productOffering.getReturnPeriod().getPeriodUnit() : 0,
                                    productOffering.getIsReplicated(),
                                    productOffering.getIsSellable());

            productOfferingResponseList.add(response);
        }

        return new ResponseEntity<>(productOfferingResponseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteOffering/{offeringId}", method = RequestMethod.GET)
    public ResponseEntity<ProductOfferingServiceResponse> deleteOffering(@PathVariable int offeringId) {
        productOfferingAppService.delete(offeringId);
        ProductOfferingServiceResponse response = new ProductOfferingServiceResponse();
        response.setMessage("The offfering with id : " + offeringId + " is created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
