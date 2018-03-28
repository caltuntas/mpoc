package com.ericsson.modernization.services.productcatalog.rest.productspeccharacteristic;

import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.ProductSpecCharacteristicAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.request.ProductSpecCharacteristicCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.request.ProductSpecCharacteristicEditRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.response.ProductSpecCharacteristicServiceResponse;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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

    @RequestMapping(value = "/deleteproductspeccharacteristic/{characteristicId}", method = RequestMethod.GET)
    public ResponseEntity<ProductSpecCharacteristicServiceResponse> deleteproductspeccharacteristic(@PathVariable int characteristicId) {
        productSpecCharacteristicAppService.delete(characteristicId);
        ProductSpecCharacteristicServiceResponse response = new ProductSpecCharacteristicServiceResponse();
        response.setMessage("The product specification characteristic with id : " + characteristicId + " is created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getcharacteristicbyid/{characteristicId}", method = RequestMethod.GET)
    public ResponseEntity<ProductSpecCharacteristic> getcharacteristicbyid(@PathVariable int characteristicId) {
        return new ResponseEntity<ProductSpecCharacteristic>(productSpecCharacteristicAppService.findById(characteristicId), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateproductspeccharacteristic", method = RequestMethod.POST)
    public ResponseEntity<ProductSpecCharacteristicServiceResponse> updateProductSpecCharacteristic(@RequestBody ProductSpecCharacteristicEditRequest productSpecCharacteristicEditRequest) {
        ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicAppService.updateCharacteristic(productSpecCharacteristicEditRequest);
        ProductSpecCharacteristicServiceResponse response = new ProductSpecCharacteristicServiceResponse();
        response.setMessage("The product specification characteristic with id : " + productSpecCharacteristic.getId() + "is updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
