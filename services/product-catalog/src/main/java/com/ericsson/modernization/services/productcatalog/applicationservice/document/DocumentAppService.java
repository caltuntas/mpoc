package com.ericsson.modernization.services.productcatalog.applicationservice.document;

import com.ericsson.modernization.services.productcatalog.applicationservice.document.request.DocumentRequest;
import com.ericsson.modernization.services.productcatalog.model.Document;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentAppService {

    @Autowired
    private DocumentRepository documentRepository;

    public void create(DocumentRequest documentRequest){

        Document document = new Document();
        //document.setCode(documentRequest.getCode());
        //document.setName(documentRequest.getName());
        //document.setDescription(documentRequest.getDescription());
        document.setDeleted(false);

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(documentRequest.getValidForEndDate());
        validFor.setValidForStartDate(documentRequest.getValidForStartDate());
        //document.setValidFor(validFor);

        documentRepository.save(document);
    }
    
    public void update(DocumentRequest documentRequest){

        Document document = documentRepository.findById(documentRequest.getId()).get();
        //document.setCode(documentRequest.getCode());
        //document.setName(documentRequest.getName());
        //document.setDescription(documentRequest.getDescription());

        TimePeriod validFor = new TimePeriod();
        validFor.setValidForEndDate(documentRequest.getValidForEndDate());
        validFor.setValidForStartDate(documentRequest.getValidForStartDate());
        //document.setValidFor(validFor);

        documentRepository.save(document);
    }

    public void delete(int id){
    	Document document = documentRepository.findById(id).get();
        if(document != null){
        	document.setDeleted(true);
            documentRepository.save(document);
        }
    }

    public Document findById(int id){
        return documentRepository.findById(id).get();
    }

    public List<Document> findAll(){
        return documentRepository.findAllByIsDeletedIsFalse();
    }
}
