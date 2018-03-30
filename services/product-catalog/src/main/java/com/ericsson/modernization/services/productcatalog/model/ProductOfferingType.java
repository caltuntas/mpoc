package com.ericsson.modernization.services.productcatalog.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductOfferingType extends EntityBase implements Description {
	private int id;
	private String name;
	private String description;
	@JsonIgnore
	@OneToMany
	private List<ProductOffering> productOfferings;

	public ProductOfferingType() {
		super();
	}
	
	public ProductOfferingType(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductOffering> getProductOfferings() {
		return productOfferings;
	}

	public void setProductOfferings(List<ProductOffering> productOfferings) {
		this.productOfferings = productOfferings;
	}

}
