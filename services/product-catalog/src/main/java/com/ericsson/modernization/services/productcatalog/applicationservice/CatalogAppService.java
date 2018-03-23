package com.ericsson.modernization.services.productcatalog.applicationservice;

import com.ericsson.modernization.services.productcatalog.applicationservice.request.CatalogCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.*;
import com.ericsson.modernization.services.productcatalog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogAppService {

    @Autowired
    private CatalogRepository catalogRepository;

    public void create(CatalogCreateRequest catalogCreateRequest){

        Catalog catalog = new Catalog();
        catalog.setName(catalogCreateRequest.getName());
        catalog.setDescription(catalogCreateRequest.getDescription());
        catalog.setExternalId(catalog.getExternalId());
        catalog.setIsReplicated(catalogCreateRequest.getReplicated());



        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(catalogCreateRequest.getValidForEndDate());
        validFor.setValidForStartDate(catalogCreateRequest.getValidForStartDate());
        catalog.setValidFor(validFor);

        CatalogSpecification catalogSpecification =  null; //TODO: Waiting for services implementations to update
        catalog.setCatalogSpecification(catalogSpecification);

        catalogRepository.save(catalog);
    }

    public void delete(int catalogID){
        Catalog catalog = catalogRepository.findById(catalogID).get();
        catalogRepository.delete(catalog);
    }

    public Catalog findById(int id){
        return catalogRepository.findById(id).get();
    }

    public List<Catalog> findAll(){
        return catalogRepository.findAll();
    }
}
