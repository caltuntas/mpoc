package com.ericsson.modernization.services.productcatalog.category;

public class CategoryCreateRequest {
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