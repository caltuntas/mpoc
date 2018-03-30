package com.ericsson.modernization.services.productcatalog.applicationservice.catalog;

import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.request.CatalogCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.catalog.request.CatalogEditRequest;
import com.ericsson.modernization.services.productcatalog.model.*;
import com.ericsson.modernization.services.productcatalog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.calendar.BaseCalendar;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogAppService {

    @Autowired
    private CatalogRepository catalogRepository;

    public Catalog create(CatalogCreateRequest catalogCreateRequest){

        Catalog catalog = new Catalog();
        catalog.setName(catalogCreateRequest.getName());
        catalog.setDescription(catalogCreateRequest.getDescription());
        catalog.setExternalId(catalog.getExternalId());
        catalog.setIsReplicated(catalogCreateRequest.getReplicated());


        TimePeriod validFor = new TimePeriod();
        //validFor.setValidForEndDate(catalogCreateRequest.getValidForEndDate());
        //validFor.setValidForStartDate(catalogCreateRequest.getValidForStartDate());
        validFor.setValidForStartDate(new Date());
        catalog.setValidFor(validFor);

        CatalogSpecification catalogSpecification =  null; //TODO: Waiting for services implementations to update
        catalog.setCatalogSpecification(catalogSpecification);

        return catalogRepository.save(catalog);
    }

    public Catalog update(CatalogEditRequest catalogEditRequest){

        Catalog catalog = findById(catalogEditRequest.getId());
        catalog.setName(catalogEditRequest.getName());
        catalog.setDescription(catalogEditRequest.getDescription());
        catalog.setExternalId(catalog.getExternalId());

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForStartDate(catalogEditRequest.getValidFor().getValidForStartDate());
        validFor.setValidForEndDate(catalogEditRequest.getValidFor().getValidForEndDate());
        catalog.setValidFor(validFor);

        CatalogSpecification catalogSpecification =  null; //TODO: Waiting for services implementations to update
        catalog.setCatalogSpecification(catalogSpecification);

        return catalogRepository.save(catalog);
    }

    public void delete(int catalogID){
        Catalog catalog = catalogRepository.findById(catalogID).get();
        if(catalog != null) {
            catalog.setDeleted(true);
            catalogRepository.save(catalog);
        }
    }

    public Catalog findById(int id){
        return catalogRepository.findByIdAndIsDeletedIsFalse(id);
    }


    public List<Catalog> findAll(){
        return catalogRepository.findAllByIsDeletedIsFalse();
    }
}
