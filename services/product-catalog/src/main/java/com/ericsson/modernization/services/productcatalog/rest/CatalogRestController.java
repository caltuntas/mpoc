package com.ericsson.modernization.services.productcatalog.rest;

import com.ericsson.modernization.services.productcatalog.applicationservice.CatalogAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.request.CatalogCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogRestController {
    @Autowired
    private CatalogAppService catalogAppService;

    @RequestMapping(value = "/createcatalog", method = RequestMethod.POST)
    public ResponseEntity<String> createCatalog(@RequestBody CatalogCreateRequest createRequest) {
        catalogAppService.create(createRequest);
        return new ResponseEntity<>("created succesfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/getallcatalogs", method = RequestMethod.GET)
    public ResponseEntity<List<Catalog>> getAllCatalogs(){
        return new ResponseEntity<List<Catalog>>( catalogAppService.findAll(), HttpStatus.OK);
    }
}
