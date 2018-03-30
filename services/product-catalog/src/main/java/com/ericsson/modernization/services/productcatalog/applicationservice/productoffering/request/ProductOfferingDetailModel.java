package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request;

import java.util.Date;
import java.util.Set;

import com.ericsson.modernization.services.productcatalog.model.Document;
import com.ericsson.modernization.services.productcatalog.model.SalesChannel;
import com.ericsson.modernization.services.productcatalog.model.Segment;

public class ProductOfferingDetailModel {
    private int id;
    private String name;
    private long warrantyPeriodValue;
    private int warrantyPeriodUnit;
    private long returnPeriodValue;
    private int returnPeriodUnit;
    private String description;
    private int productSpecificationId;
    private int catalogId;
    private Boolean isReplicated;
    private Boolean isSellable;
    private Set<SalesChannel> salesChannels;
    private Set<Segment> segments;
    private Set<Document> documents;

    public ProductOfferingDetailModel
            (int id,
             String name,
             String description,
             Boolean isReplicated,
             Boolean isSellable,
             int productSpecificationId,
             int catalogId
            ) {
        setId(id);
        setName(name);
        setDescription(description);
        setIsReplicated(isReplicated);
        setIsSellable(isSellable);
        setProductSpecificationId(productSpecificationId);
        setCatalogId(catalogId);
    }

    public ProductOfferingDetailModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWarrantyPeriodValue() {
        return warrantyPeriodValue;
    }

    public void setWarrantyPeriodValue(long warrantyPeriodValue) {
        this.warrantyPeriodValue = warrantyPeriodValue;
    }

    public int getWarrantyPeriodUnit() {
        return warrantyPeriodUnit;
    }

    public void setWarrantyPeriodUnit(int warrantyPeriodUnit) {
        this.warrantyPeriodUnit = warrantyPeriodUnit;
    }

    public long getReturnPeriodValue() {
        return returnPeriodValue;
    }

    public void setReturnPeriodValue(long returnPeriodValue) {
        this.returnPeriodValue = returnPeriodValue;
    }

    public int getReturnPeriodUnit() {
        return returnPeriodUnit;
    }

    public void setReturnPeriodUnit(int returnPeriodUnit) {
        this.returnPeriodUnit = returnPeriodUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsReplicated() {
        return isReplicated;
    }

    public void setIsReplicated(Boolean replicated) {
        isReplicated = replicated;
    }

    public Boolean getIsSellable() {
        return isSellable;
    }

    public void setIsSellable(Boolean sellable) {
        isSellable = sellable;
    }

    public int getProductSpecificationId() {
        return productSpecificationId;
    }

    public void setProductSpecificationId(int productSpecificationId) {
        this.productSpecificationId = productSpecificationId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<SalesChannel> getSalesChannels() {
        return this.salesChannels;
    }

    public void setSalesChannels(Set<SalesChannel> salesChannels) {
    	this.salesChannels = salesChannels;
    }

    public Set<Segment> getSegments() {
    	return this.segments;
    }

    public void setSegments(Set<Segment> segments) {
    	this.segments = segments;
    }

    public Set<Document> getDocuments() {
    	return this.documents;
    }

    public void setDocuments(Set<Document> documents) {
    	this.documents = documents;
    }
}
