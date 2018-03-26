package com.ericsson.modernization.services.productcatalog.rest.SalesChannel;

import com.ericsson.modernization.services.productcatalog.applicationservice.SalesChannel.SalesChannelAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.SalesChannel.request.SalesChannelRequest;
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
    public ResponseEntity<String> createSalesChannel(@RequestBody SalesChannelRequest createRequest) {
    	salesChannelAppService.create(createRequest);
        return new ResponseEntity<>("created succesfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatesaleschannel", method = RequestMethod.POST)
    public ResponseEntity<String> updateSalesChannel(@RequestBody SalesChannelRequest createRequest) {
    	salesChannelAppService.update(createRequest);
        return new ResponseEntity<>("updated succesfully", HttpStatus.OK);
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
    public ResponseEntity<String> deleteSalesChannel(@RequestParam(required=true) int id){
    	salesChannelAppService.delete(id);
    	return new ResponseEntity<>("updated succesfully", HttpStatus.OK);
    }
}
