package com.ericsson.modernization.services.productcatalog.rest.document;

import com.ericsson.modernization.services.productcatalog.applicationservice.document.DocumentAppService;
import com.ericsson.modernization.services.productcatalog.applicationservice.document.request.DocumentRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.document.response.DocumentResponse;
import com.ericsson.modernization.services.productcatalog.model.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentRestController {
    @Autowired
    private DocumentAppService documentAppService = new DocumentAppService();

    @RequestMapping(value = "/createdocument", method = RequestMethod.POST)
    public ResponseEntity<DocumentResponse> createDocument(@RequestBody DocumentRequest createRequest) {
        documentAppService.create(createRequest);
    	DocumentResponse resp = new DocumentResponse();
    	resp.setMessage("created succesfully");
        return new ResponseEntity<DocumentResponse>(resp, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/updatedocument", method = RequestMethod.POST)
    public ResponseEntity<DocumentResponse> updateDocument(@RequestBody DocumentRequest createRequest) {
    	documentAppService.update(createRequest);
    	DocumentResponse resp = new DocumentResponse();
    	resp.setMessage("updated succesfully");
        return new ResponseEntity<DocumentResponse>(resp, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getalldocuments", method = RequestMethod.GET)
    public ResponseEntity<List<Document>> getAllDocuments(){
        return new ResponseEntity<List<Document>>( documentAppService.findAll(), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getdocument", method = RequestMethod.GET)
    public ResponseEntity<Document> getDocument(@RequestParam(required=true) int id){
        return new ResponseEntity<Document>( documentAppService.findById(id), HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/deletedocument", method = RequestMethod.GET)
    public ResponseEntity<DocumentResponse> deleteDocument(@RequestParam(required=true) int id){
    	documentAppService.delete(id);
    	DocumentResponse resp = new DocumentResponse();
    	resp.setMessage("deleted succesfully");
    	return new ResponseEntity<DocumentResponse>(resp, HttpStatus.OK);
    }
}
