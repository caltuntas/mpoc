package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class ProductOffering extends EntityBase implements Description, ValidFor, ExternalId, IsReplicated, Versioned {

    public ProductOffering() {
        prices = new ArrayList<ProductOfferingPrice>();
        setProductOfferingDetermineses(new ArrayList<ProductOfferingDetermines>());
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
    //@ManyToOne
    //private ProductOfferingTerm productOfferingTerm;

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
    @OneToMany(mappedBy = "productOffering",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProductOfferingPrice> prices;
    @JsonIgnore
    @OneToMany(mappedBy = "productOffering",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProductOfferingDetermines> productOfferingDetermineses;
    @JsonIgnore
    @OneToMany(mappedBy = "productOffering",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<UnsupportedProductSpecCharValueUseGroup> unsupportedProductSpecCharValueUseGroups;
    @JsonIgnore
    @OneToMany(mappedBy = "productOffering",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Category> category;

    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ProductOfferingSalesChannels", joinColumns = @JoinColumn(name = "productoffering_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "saleschannel_id", referencedColumnName = "id"))
    private Set<SalesChannel> salesChannels;
    public Set<SalesChannel> getSalesChannels() {
        return salesChannels;
    }

    public void setSalesChannels(Set<SalesChannel> salesChannels) {
        this.salesChannels = salesChannels;
    }
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ProductOfferingSegments", joinColumns = @JoinColumn(name = "productoffering_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segment_id", referencedColumnName = "id"))
    private Set<Segment> segments;
    public Set<Segment> getSegments() {
        return segments;
    }

    public void setSegments(Set<Segment> segments) {
        this.segments = segments;
    }
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ProductOfferingDocuments", joinColumns = @JoinColumn(name = "productoffering_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"))
    private Set<Document> documents;
    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	private ProductOfferingType productOfferingType;
    public ProductOfferingType getProductOfferingType() {
        return productOfferingType;
    }

    public void setProductOfferingTypeId(ProductOfferingType productOfferingType) {
        this.productOfferingType = productOfferingType;
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

    public List<ProductOfferingDetermines> getProductOfferingDetermineses() {
        return productOfferingDetermineses;
    }

    public void setProductOfferingDetermineses(List<ProductOfferingDetermines> productOfferingDetermineses) {
		this.productOfferingDetermineses = productOfferingDetermineses;
	}
}
