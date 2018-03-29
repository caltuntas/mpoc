package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class ProductOfferingType extends EntityBase implements Description {
	private int id;
	private String name;
	private String description;
	@OneToOne
	@PrimaryKeyJoinColumn
	private ProductOffering productOffering;
	public ProductOfferingType(int id, String name, String description) {
		super();
		this.id = id;
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
	public ProductOffering getProductOffering() {
		return productOffering;
	}
	public void setProductOffering(ProductOffering productOffering) {
		this.productOffering = productOffering;
	}
	
 

}
