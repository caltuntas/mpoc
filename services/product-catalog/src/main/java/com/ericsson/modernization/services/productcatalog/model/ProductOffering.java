package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;

import com.ericsson.modernization.services.productcatalog.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductOffering extends EntityBase implements Description, ValidFor, ExternalId, IsReplicated, Versioned {

    public ProductOffering() {
        prices = new ArrayList<ProductOfferingPrice>();
        determinedProdSpecCharValueUses = new ArrayList<ProdSpecCharValueUse>();
        unsupportedProductSpecCharValueUseGroups = new ArrayList<UnsupportedProductSpecCharValueUseGroup>();
        category = new ArrayList<Category>();
    }

    private String name;
    @Embedded
    private TimePeriod validFor;
    private String description;
    private String externalId;
    @ManyToOne
    private ProductSpecification productSpecification;
    @ManyToOne
    private Catalog catalog;
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
    private Boolean isReplicated;
    private Boolean isSellable;
    private long versionNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "productOffering")
    private List<ProductOfferingPrice> prices;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "productOffering")
    private List<ProdSpecCharValueUse> determinedProdSpecCharValueUses;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "productOffering")
    private List<UnsupportedProductSpecCharValueUseGroup> unsupportedProductSpecCharValueUseGroups;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Category> category;

    public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
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

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
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

    public Boolean getIsReplicated() {
        return isReplicated;
    }

    public void setIsReplicated(Boolean isReplicated) {
        this.isReplicated = isReplicated;
    }

    public Boolean getIsSellable() {
        return isSellable;
    }

    public void setIsSellable(Boolean isSellable) {
        this.isSellable = isSellable;
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(long versionNumber) {
        this.versionNumber = versionNumber;
    }

    public List<ProductOfferingPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductOfferingPrice> prices) {
        this.prices = prices;
    }

    public List<ProdSpecCharValueUse> getDeterminedProdSpecCharValueUses() {
        return determinedProdSpecCharValueUses;
    }

    public void setDeterminedProdSpecCharValueUses(List<ProdSpecCharValueUse> determinedProdSpecCharValueUses) {
        this.determinedProdSpecCharValueUses = determinedProdSpecCharValueUses;
    }

    public List<UnsupportedProductSpecCharValueUseGroup> getUnsupportedProductSpecCharValueUseGroups() {
        return unsupportedProductSpecCharValueUseGroups;
    }

    public void setUnsupportedProductSpecCharValueUseGroups(
            List<UnsupportedProductSpecCharValueUseGroup> unsupportedProductSpecCharValueUseGroups) {
        this.unsupportedProductSpecCharValueUseGroups = unsupportedProductSpecCharValueUseGroups;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
