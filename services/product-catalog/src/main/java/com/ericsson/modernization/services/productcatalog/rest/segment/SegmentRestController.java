package com.ericsson.modernization.services.productcatalog.rest.segment;


import com.ericsson.modernization.services.productcatalog.applicationservice.segment.SegmentAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.segment.request.SegmentRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.segment.response.SegmentServiceResponse;
import com.ericsson.modernization.services.productcatalog.model.Segment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segment")
public class SegmentRestController {
    @Autowired
    private SegmentAppService salesChannelAppService;

    @RequestMapping(value = "/createsegment", method = RequestMethod.POST)
    public ResponseEntity<SegmentServiceResponse> createSegment(@RequestBody SegmentRequest createRequest) {
    	salesChannelAppService.create(createRequest);
    	SegmentServiceResponse resp = new SegmentServiceResponse();
    	resp.setMessage("created succesfully");
        return new ResponseEntity<SegmentServiceResponse>(resp, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatesegment", method = RequestMethod.POST)
    public ResponseEntity<SegmentServiceResponse> updateSegment(@RequestBody SegmentRequest createRequest) {
    	salesChannelAppService.update(createRequest);
    	SegmentServiceResponse resp = new SegmentServiceResponse();
    	resp.setMessage("updated succesfully");
        return new ResponseEntity<SegmentServiceResponse>(resp, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getallsegments", method = RequestMethod.GET)
    public ResponseEntity<List<Segment>> getAllSegments(){
        return new ResponseEntity<List<Segment>>( salesChannelAppService.findAll(), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getsegment", method = RequestMethod.GET)
    public ResponseEntity<Segment> getSegment(@RequestParam(required=true) int id){
        return new ResponseEntity<Segment>( salesChannelAppService.findById(id), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/deletesegment", method = RequestMethod.GET)
    public ResponseEntity<SegmentServiceResponse> deleteSegment(@RequestParam(required=true) int id){
    	salesChannelAppService.delete(id);
    	SegmentServiceResponse resp = new SegmentServiceResponse();
    	resp.setMessage("deleted succesfully");
    	return new ResponseEntity<SegmentServiceResponse>(resp, HttpStatus.OK);
    }
}
