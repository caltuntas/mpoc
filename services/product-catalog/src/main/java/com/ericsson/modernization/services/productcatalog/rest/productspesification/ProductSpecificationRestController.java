
package com.ericsson.modernization.services.productcatalog.rest.productspesification;


import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response.ProdSpecCharListResponse;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.ProductSpecificationAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.request.ProductSpecificationCreateRequest;

import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response.ProductSpecListModel;
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

    @RequestMapping(value = "/createSpec", method = RequestMethod.POST)
    public ResponseEntity<String> createProductSpecification(@RequestBody ProductSpecificationCreateRequest createRequest) {
        productSpecificationAppService.create(createRequest);
        return new ResponseEntity<>("created succesfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/getCharacteristics", method = RequestMethod.GET)
    public List<ProdSpecCharListResponse> getCharacteristics() {
       return productSpecificationAppService.getCharacteristics();

    }
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<ProductSpecListModel>> getAllOfferings(){
        return new ResponseEntity<List<ProductSpecListModel>>( productSpecificationAppService.getSpecs(), HttpStatus.OK);
    }
}
