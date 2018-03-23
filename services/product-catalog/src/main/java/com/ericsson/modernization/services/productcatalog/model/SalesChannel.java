package com.ericsson.modernization.services.productcatalog.model;
import javax.persistence.*;

@Entity
public class SalesChannel extends EntityBase implements Description, ValidFor{
	private String code;
	private String name;
	private String description;
	@Embedded
	private TimePeriod validFor;
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
	public TimePeriod getValidFor() {
		return validFor;
	}
	public void setValidFor(TimePeriod validFor) {
		this.validFor = validFor;
	}
	
	
}
