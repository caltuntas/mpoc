
package com.ericsson.modernization.services.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ProductSpecification extends EntityBase
        implements Description, ExternalId, IsReplicated, Versioned {
    public ProductSpecification() {
        productSpecCharUses = new ArrayList<ProductSpecCharUse>();
    }

    private String name;
    private String code;
    private String description;
    private String serialNumberFormat;
    @ManyToOne
    private ProductSpecificationStatus status;
    @ManyToOne
    private ProductType productType;
    @AttributeOverrides({
            @AttributeOverride(name = "periodValue", column = @Column(name = "warrantyPeriodValue")),
            @AttributeOverride(name = "periodUnit", column = @Column(name = "warrantyPeriodUnit"))
    })
    @Embedded
    private Duration warrantyPeriod;
    @AttributeOverrides({
            @AttributeOverride(name = "periodValue", column = @Column(name = "returnPeriodValue")),
            @AttributeOverride(name = "periodUnit", column = @Column(name = "returnPeriodUnit"))
    })
    @Embedded
    private Duration returnPeriod;
    private String externalId;
    @JsonIgnore
    @OneToMany(mappedBy = "productSpecification")
    private List<ProductSpecCharUse> productSpecCharUses;
    private String article;
    private Boolean isReplicated;

    private long versionNumber;

    public void addCharUse(ProductSpecCharUse charuse) {
        this.productSpecCharUses.add(charuse);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumberFormat() {
        return serialNumberFormat;
    }

    public void setSerialNumberFormat(String serialNumberFormat) {
        this.serialNumberFormat = serialNumberFormat;
    }

    public ProductSpecificationStatus getStatus() {
        return status;
    }

    public void setStatus(ProductSpecificationStatus status) {
        this.status = status;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Duration getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Duration warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Duration getReturnPeriod() {
        return returnPeriod;
    }

    public void setReturnPeriod(Duration returnPeriod) {
        this.returnPeriod = returnPeriod;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<ProductSpecCharUse> getProductSpecCharUses() {
        return productSpecCharUses;
    }

    public void setProductSpecCharUses(List<ProductSpecCharUse> productSpecCharUses) {
        this.productSpecCharUses = productSpecCharUses;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Boolean getIsReplicated() {
        return getReplicated();
    }

    public void setIsReplicated(Boolean isReplicated) {
        this.setReplicated(isReplicated);
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(long versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Boolean getReplicated() {
        return isReplicated;
    }

    public void setReplicated(Boolean replicated) {
        isReplicated = replicated;
    }
}
