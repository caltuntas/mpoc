package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request;

import java.util.ArrayList;
import java.util.List;

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
    private int categoryId;
    private int term;
    private Boolean isReplicated;
    private Boolean isSellable;
    private List<Integer> salesChannels;
    private List<Integer> segments;
    private List<Integer> documents;
    private int productOfferingTypeId;
    private List<Integer> simpleProductOfferingIds = new ArrayList<Integer>();
    private List<ProductOfferingCharValueModel> productOfferingCharValues;

    public ProductOfferingDetailModel
            (int id,
             String name,
             String description,
             Boolean isReplicated,
             Boolean isSellable,
             int productSpecificationId,
             int catalogId,
             int categoryId,
             int productOfferingTypeId,
             int term,
             List<Integer> simpleProductOfferingIds,
             List<ProductOfferingCharValueModel> productOfferingCharValues,
             List<Integer> salesChannels,
             List<Integer> segments,
             List<Integer> documents) {
        setId(id);
        setName(name);
        setDescription(description);
        setIsReplicated(isReplicated);
        setIsSellable(isSellable);
        setProductSpecificationId(productSpecificationId);
        setCatalogId(catalogId);
        setCategoryId(categoryId);
        setProductOfferingTypeId(productOfferingTypeId);
        setSimpleProductOfferingIds(simpleProductOfferingIds);
        setProductOfferingCharValues(productOfferingCharValues);
        setSalesChannels(salesChannels);
        setSegments(segments);
        setDocuments(documents);
        setTerm(term);
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

    public int getProductOfferingTypeId() {
        return productOfferingTypeId;
    }

    public void setProductOfferingTypeId(int productOfferingTypeId) {
        this.productOfferingTypeId = productOfferingTypeId;
    }

    public List<Integer> getSimpleProductOfferingIds() {
        return simpleProductOfferingIds;
    }

    public void setSimpleProductOfferingIds(List<Integer> simpleProductOfferingIds) {
        this.simpleProductOfferingIds = simpleProductOfferingIds;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductOfferingCharValueModel> getProductOfferingCharValues() {
        return productOfferingCharValues;
    }

    public void setProductOfferingCharValues(List<ProductOfferingCharValueModel> productOfferingCharValues) {
        this.productOfferingCharValues = productOfferingCharValues;
    }

    public List<Integer> getSalesChannels() {
        return salesChannels;
    }

    public void setSalesChannels(List<Integer> salesChannels) {
        this.salesChannels = salesChannels;
    }

    public List<Integer> getSegments() {
        return segments;
    }

    public void setSegments(List<Integer> segments) {
        this.segments = segments;
    }

    public List<Integer> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Integer> documents) {
        this.documents = documents;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
