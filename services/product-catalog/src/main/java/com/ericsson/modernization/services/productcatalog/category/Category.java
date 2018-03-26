package com.ericsson.modernization.services.productcatalog.category;

import javax.persistence.Entity;

import com.ericsson.modernization.services.productcatalog.model.EntityBase;

@Entity
public class Category extends EntityBase {
	
	private String code;
	private String name;
	private String description;
	private int parentId;
	private Boolean isRoot;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}


}
