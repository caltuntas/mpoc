package com.ericsson.modernization.services.productcatalog.rest.saleschannel;

import com.ericsson.modernization.services.productcatalog.applicationservice.saleschannel.SalesChannelAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.saleschannel.request.SalesChannelRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.saleschannel.response.SalesChannelServiceResponse;
import com.ericsson.modernization.services.productcatalog.model.SalesChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saleschannel")
public class SalesChannelRestController {
    @Autowired
    private SalesChannelAppService salesChannelAppService;

    @RequestMapping(value = "/createsaleschannel", method = RequestMethod.POST)
    public ResponseEntity<SalesChannelServiceResponse> createSalesChannel(@RequestBody SalesChannelRequest createRequest) {
    	salesChannelAppService.create(createRequest);
    	SalesChannelServiceResponse resp = new SalesChannelServiceResponse();
    	resp.setMessage("created succesfully");
        return new ResponseEntity<SalesChannelServiceResponse>(resp, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatesaleschannel", method = RequestMethod.POST)
    public ResponseEntity<SalesChannelServiceResponse> updateSalesChannel(@RequestBody SalesChannelRequest createRequest) {
    	salesChannelAppService.update(createRequest);
    	SalesChannelServiceResponse resp = new SalesChannelServiceResponse();
    	resp.setMessage("updated succesfully");
        return new ResponseEntity<SalesChannelServiceResponse>(resp, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getallsaleschannels", method = RequestMethod.GET)
    public ResponseEntity<List<SalesChannel>> getAllSalesChannels(){
        return new ResponseEntity<List<SalesChannel>>( salesChannelAppService.findAll(), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getsaleschannel", method = RequestMethod.GET)
    public ResponseEntity<SalesChannel> getSalesChannel(@RequestParam(required=true) int id){
        return new ResponseEntity<SalesChannel>( salesChannelAppService.findById(id), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/deletesaleschannel", method = RequestMethod.GET)
    public ResponseEntity<SalesChannelServiceResponse> deleteSalesChannel(@RequestParam(required=true) int id){
    	salesChannelAppService.delete(id);
    	SalesChannelServiceResponse resp = new SalesChannelServiceResponse();
    	resp.setMessage("deleted succesfully");
    	return new ResponseEntity<SalesChannelServiceResponse>(resp, HttpStatus.OK);
    }
}
