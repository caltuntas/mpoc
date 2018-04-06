package com.ericsson.modernization.services.productcatalog.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductOffering extends EntityBase implements Description, ValidFor, ExternalId, IsReplicated, Versioned {

	public ProductOffering() {
		setPrices(new ArrayList<>());
		setProductOfferingDetermineses(new ArrayList<>());
		setUnsupportedProductSpecCharValueUseGroups(new ArrayList<>());
		setSalesChannels(new ArrayList<>());
		setSegments(new ArrayList<>());
		setDocuments(new ArrayList<>());
		setCreateUserDate(new Date());

		TimePeriod validFor = new TimePeriod();
		validFor.setValidForStartDate(new Date());
		setValidFor(validFor);
	}

	private String name;
	@Embedded
	private TimePeriod validFor;
	private String description;
	private String externalId;
	@ManyToOne
	private ProductSpecification productSpecification;
	@OneToOne(cascade = CascadeType.ALL)
	private ProductOfferingTerm productOfferingTerm;
	@ManyToOne
	private Category category;

	@ManyToOne
	private Catalog catalog;
	@AttributeOverrides({ @AttributeOverride(name = "periodValue", column = @Column(name = "warrantyPeriodValue")),
			@AttributeOverride(name = "periodUnit", column = @Column(name = "warrantyPeriodUnit")) })
	@Embedded
	private Duration warrantyPeriod;
	@AttributeOverrides({ @AttributeOverride(name = "periodValue", column = @Column(name = "returnPeriodValue")),
			@AttributeOverride(name = "periodUnit", column = @Column(name = "returnPeriodUnit")) })
	@Embedded
	private Duration returnPeriod;
	private Boolean isReplicated;
	private Boolean isSellable;
	private long versionNumber;

	@JsonIgnore
	@OneToMany(mappedBy = "productOffering", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ProductOfferingPrice> prices;
	@JsonIgnore
	@OneToMany(mappedBy = "productOffering", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ProductOfferingDetermines> productOfferingDetermineses;
	@JsonIgnore
	@OneToMany(mappedBy = "productOffering", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<UnsupportedProductSpecCharValueUseGroup> unsupportedProductSpecCharValueUseGroups;	
	@JsonIgnore
	@ManyToOne
	private ProductOfferingType productOfferingType;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ProductOfferingSalesChannels", joinColumns = @JoinColumn(name = "productoffering_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "saleschannel_id", referencedColumnName = "id"))
	private List<SalesChannel> salesChannels;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ProductOfferingSegments", joinColumns = @JoinColumn(name = "productoffering_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "segment_id", referencedColumnName = "id"))
	private List<Segment> segments;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ProductOfferingDocuments", joinColumns = @JoinColumn(name = "productoffering_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "id"))
	private List<Document> documents;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ProductOfferingRelation", 
	joinColumns = @JoinColumn(name = "productofferingId", referencedColumnName = "id", nullable = false), 
	inverseJoinColumns = @JoinColumn(name = "relatedProductofferingId", referencedColumnName = "id", nullable = false))
	private List<ProductOffering> productOfferings;
	private String code;
    @ManyToOne
    private ProductOffering clonnedProductOffering;
    @OneToMany(mappedBy="clonnedProductOffering")
    private Collection<ProductOffering> childrenProductOfferings;
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
	public ProductOfferingTerm getProductOfferingTerm() {
		return productOfferingTerm;
	}
	public void setProductOfferingTerm(ProductOfferingTerm productOfferingTerm) {
		this.productOfferingTerm = productOfferingTerm;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
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
	public List<ProductOfferingDetermines> getProductOfferingDetermineses() {
		return productOfferingDetermineses;
	}
	public void setProductOfferingDetermineses(List<ProductOfferingDetermines> productOfferingDetermineses) {
		this.productOfferingDetermineses = productOfferingDetermineses;
	}
	public List<UnsupportedProductSpecCharValueUseGroup> getUnsupportedProductSpecCharValueUseGroups() {
		return unsupportedProductSpecCharValueUseGroups;
	}
	public void setUnsupportedProductSpecCharValueUseGroups(
			List<UnsupportedProductSpecCharValueUseGroup> unsupportedProductSpecCharValueUseGroups) {
		this.unsupportedProductSpecCharValueUseGroups = unsupportedProductSpecCharValueUseGroups;
	}
	public ProductOfferingType getProductOfferingType() {
		return productOfferingType;
	}
	public void setProductOfferingType(ProductOfferingType productOfferingType) {
		this.productOfferingType = productOfferingType;
	}
	public List<SalesChannel> getSalesChannels() {
		return salesChannels;
	}
	public void setSalesChannels(List<SalesChannel> salesChannels) {
		this.salesChannels = salesChannels;
	}
	public List<Segment> getSegments() {
		return segments;
	}
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	public List<ProductOffering> getProductOfferings() {
		return productOfferings;
	}
	public void setProductOfferings(List<ProductOffering> productOfferings) {
		this.productOfferings = productOfferings;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ProductOffering getClonnedProductOffering() {
		return clonnedProductOffering;
	}
	public void setClonnedProductOffering(ProductOffering clonnedProductOffering) {
		this.clonnedProductOffering = clonnedProductOffering;
	}
	public Collection<ProductOffering> getChildrenProductOfferings() {
		return childrenProductOfferings;
	}
	public void setChildrenProductOfferings(Collection<ProductOffering> childrenProductOfferings) {
		this.childrenProductOfferings = childrenProductOfferings;
	}
	
    
}
