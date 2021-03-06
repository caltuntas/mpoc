package com.ericsson.modernization.services.productcatalog.applicationservice.category.response;

//import com.fasterxml.jackson.annotation.JsonFormat;

//import java.util.Date;

public class CategoryListModel {
	private int id;
	private String code;
	private String name;
	private String description;
	private int parentId;
	private String parentName;

	// private Date validForStartDate;
	// private Date validForEndDate;
	// @JsonFormat(pattern="yyyy-MM-dd")
	// Date validForStartDate,
	// @JsonFormat(pattern="yyyy-MM-dd")
	// Date validForEndDate,
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public CategoryListModel(int id, String code, String name, String description, int parentId, String parentName) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.parentId = parentId;
		this.parentName = parentName;
	}

	public CategoryListModel(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
