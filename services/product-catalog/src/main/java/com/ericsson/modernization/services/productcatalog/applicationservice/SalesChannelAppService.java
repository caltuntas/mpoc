package com.ericsson.modernization.services.productcatalog.applicationservice;

import com.ericsson.modernization.services.productcatalog.applicationservice.request.SalesChannelRequest;
import com.ericsson.modernization.services.productcatalog.model.SalesChannel;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.repository.SalesChannelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesChannelAppService {

    @Autowired
    private SalesChannelRepository salesChannelRepository;

    public void create(SalesChannelRequest salesChannelRequest){

        SalesChannel salesChannel = new SalesChannel();
        salesChannel.setCode(salesChannelRequest.getCode());
        salesChannel.setName(salesChannelRequest.getName());
        salesChannel.setDescription(salesChannelRequest.getDescription());
        salesChannel.setDeleted(false);

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(salesChannelRequest.getValidForEndDate());
        validFor.setValidForStartDate(salesChannelRequest.getValidForStartDate());
        salesChannel.setValidFor(validFor);

        salesChannelRepository.save(salesChannel);
    }
    
    public void update(SalesChannelRequest salesChannelRequest){

        SalesChannel salesChannel = salesChannelRepository.findById(salesChannelRequest.getId()).get();
        salesChannel.setCode(salesChannelRequest.getCode());
        salesChannel.setName(salesChannelRequest.getName());
        salesChannel.setDescription(salesChannelRequest.getDescription());

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(salesChannelRequest.getValidForEndDate());
        validFor.setValidForStartDate(salesChannelRequest.getValidForStartDate());
        salesChannel.setValidFor(validFor);

        salesChannelRepository.save(salesChannel);
    }

    public void delete(int id){
    	SalesChannel salesChannel = salesChannelRepository.findById(id).get();
        if(salesChannel != null){
        	salesChannel.setDeleted(true);
            salesChannelRepository.save(salesChannel);
        }
    }

    public SalesChannel findById(int id){
        return salesChannelRepository.findById(id).get();
    }

    public List<SalesChannel> findAll(){
    	//SalesChannel ex = new SalesChannel();
    	//ex.setDeleted(false);
    	
    	//ExampleMatcher exampleMatcher = ExampleMatcher.matching()
        //        .withIgnoreNullValues()
        //        .withIgnoreCase(); 
    	
    	//Example<SalesChannel> example = Example.of(ex,exampleMatcher);
        return salesChannelRepository.findAll();
    }
}
