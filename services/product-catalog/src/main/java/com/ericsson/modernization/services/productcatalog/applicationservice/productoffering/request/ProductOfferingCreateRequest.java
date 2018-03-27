package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.request;

import java.util.Date;

public class ProductOfferingCreateRequest {
    private String name;
    private Date validForStartDate;
    private Date validForEndDate;
    private long warrantyPeriodValue;
    private int warrantyPeriodUnit;
    private long returnPeriodValue;
    private int returnPeriodUnit;
    private String description;
    private String externalId;
    private int productSpecificationId;
    private Boolean isReplicated;
    private Boolean isSellable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidForStartDate() {
        return validForStartDate;
    }

    public void setValidForStartDate(Date validForStartDate) {
        this.validForStartDate = validForStartDate;
    }

    public Date getValidForEndDate() {
        return validForEndDate;
    }

    public void setValidForEndDate(Date validForEndDate) {
        this.validForEndDate = validForEndDate;
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }

    public Boolean getSellable() {
        return isSellable;
    }

    public void setSellable(Boolean sellable) {
        isSellable = sellable;
    }

    public int getProductSpecificationId() {
        return productSpecificationId;
    }

    public void setProductSpecificationId(int productSpecificationId) {
        this.productSpecificationId = productSpecificationId;
    }
}
