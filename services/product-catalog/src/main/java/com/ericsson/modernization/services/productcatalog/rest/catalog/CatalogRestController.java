package com.ericsson.modernization.services.productcatalog.rest.catalog;

import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.CatalogAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.request.CatalogCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.CommonServiceResponse;
import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.request.CatalogEditRequest;
import com.ericsson.modernization.services.productcatalog.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogRestController {
    @Autowired
    private CatalogAppService catalogAppService;

    @RequestMapping(value = "/createcatalog", method = RequestMethod.POST)
    public ResponseEntity<CommonServiceResponse> createCatalog(@RequestBody CatalogCreateRequest createRequest) {
        Catalog catalog = catalogAppService.create(createRequest);
        CommonServiceResponse response = new CommonServiceResponse();
        response.setMessage("An catalog with id : " + catalog.getId() + " was created succesfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getallcatalogs", method = RequestMethod.GET)
    public ResponseEntity<List<Catalog>> getAllCatalogs(){
        return new ResponseEntity<List<Catalog>>( catalogAppService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCatalog/{catalogId}", method = RequestMethod.GET)
    public ResponseEntity<CommonServiceResponse> deleteCatalog(@PathVariable int catalogId) {
        catalogAppService.delete(catalogId);
        CommonServiceResponse response = new CommonServiceResponse();
        response.setMessage("The catalog with id : " + catalogId + " was deleted succesfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getcatalogbyid/{catalogId}", method = RequestMethod.GET)
    public ResponseEntity<Catalog> getcatalogbyid(@PathVariable int catalogId) {
        return new ResponseEntity<Catalog>(catalogAppService.findById(catalogId), HttpStatus.OK);
    }

    @RequestMapping(value = "/updatecatalog", method = RequestMethod.POST)
    public ResponseEntity<CommonServiceResponse> updatecatalog(@RequestBody CatalogEditRequest updateRequest) {
        Catalog catalog = catalogAppService.update(updateRequest);
        CommonServiceResponse response = new CommonServiceResponse();
        response.setMessage("An catalog with id : " + catalog.getId() + " was updated succesfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
