package com.ericsson.modernization.services.productcatalog.applicationservice.segment;

import com.ericsson.modernization.services.productcatalog.applicationservice.saleschannel.request.SalesChannelRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.segment.request.SegmentRequest;
import com.ericsson.modernization.services.productcatalog.model.Segment;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.repository.SegmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegmentAppService {

    @Autowired
    private SegmentRepository segmentRepository;

    public void create(SegmentRequest segmentRequest){

    	Segment segment = new Segment();
        segment.setCode(segmentRequest.getCode());
        segment.setName(segmentRequest.getName());
        segment.setDescription(segmentRequest.getDescription());
        segment.setDeleted(false);

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(segmentRequest.getValidForEndDate());
        validFor.setValidForStartDate(segmentRequest.getValidForStartDate());
        segment.setValidFor(validFor);

        segmentRepository.save(segment);
    }
    
    public void update(SegmentRequest segmentRequest){

    	Segment segment = segmentRepository.findById(segmentRequest.getId()).get();
        segment.setCode(segmentRequest.getCode());
        segment.setName(segmentRequest.getName());
        segment.setDescription(segmentRequest.getDescription());

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(segmentRequest.getValidForEndDate());
        validFor.setValidForStartDate(segmentRequest.getValidForStartDate());
        segment.setValidFor(validFor);

        segmentRepository.save(segment);
    }

    public void delete(int id){
    	Segment segment = segmentRepository.findById(id).get();
        if(segment != null){
        	segment.setDeleted(true);
        	segmentRepository.save(segment);
        }
    }

    public Segment findById(int id){
        return segmentRepository.findById(id).get();
    }

    public List<Segment> findAll(){
        return segmentRepository.findAllByIsDeletedIsFalse();
    }
}
