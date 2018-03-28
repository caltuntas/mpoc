package com.ericsson.modernization.services.productcatalog.applicationservice.productoffering.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProductOfferingListModel {
    private int id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validForStartDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validForEndDate;
    private String description;
    private long warrantyPeriodValue;
    private int warrantyPeriodUnit;
    private long returnPeriodValue;
    private int returnPeriodUnit;
    private Boolean isReplicated = false;
    private Boolean isSellable = false;
    private String productSpesificationCode;
    private String catalogCode;

    public ProductOfferingListModel(
            int id,
            String name,
            String description,
            String productSpesificationCode,
            String catalogCode,
            Boolean isReplicated,
            Boolean isSellable,
            Date validForStartDate,
            Date validForEndDate,
            long warrantyPeriodValue,
            int warrantyPeriodUnit,
            long returnPeriodValue,
            int returnPeriodUnit) {
        setId(id);
        setName(name);
        setDescription(description);
        setProductSpesificationCode(productSpesificationCode);
        setCatalogCode(catalogCode);
        setReplicated(isReplicated);
        setIsSellable(isSellable);
        setValidForStartDate(validForStartDate);
        setValidForEndDate(validForEndDate);
        setReturnPeriodValue(returnPeriodValue);
        setReturnPeriodUnit(returnPeriodUnit);
        setWarrantyPeriodUnit(warrantyPeriodUnit);
        setWarrantyPeriodValue(warrantyPeriodValue);
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }

    public Boolean getIsSellable() {
        return isSellable;
    }

    public void setIsSellable(Boolean sellable) {
        isSellable = sellable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductSpesificationCode() {
        return productSpesificationCode;
    }

    public void setProductSpesificationCode(String productSpesificationCode) {
        this.productSpesificationCode = productSpesificationCode;
    }

    public String getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(String catalogCode) {
        this.catalogCode = catalogCode;
    }
}


