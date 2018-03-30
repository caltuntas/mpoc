package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductOfferingRelation extends EntityBase {
	@Id
	private int id;
	private int mainProductOfferingId;
	private int relatedProductOfferingId;

	public ProductOfferingRelation() {
		super();
	}

	public ProductOfferingRelation(int mainProductOfferingId, int relatedProductOfferingId) {
		super();
		this.mainProductOfferingId = mainProductOfferingId;
		this.relatedProductOfferingId = relatedProductOfferingId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMainProductOfferingId() {
		return mainProductOfferingId;
	}

	public void setMainProductOfferingId(int mainProductOfferingId) {
		this.mainProductOfferingId = mainProductOfferingId;
	}

	public int getRelatedProductOfferingId() {
		return relatedProductOfferingId;
	}

	public void setRelatedProductOfferingId(int relatedProductOfferingId) {
		this.relatedProductOfferingId = relatedProductOfferingId;
	}

}
