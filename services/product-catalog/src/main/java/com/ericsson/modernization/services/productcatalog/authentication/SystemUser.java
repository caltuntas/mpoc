package com.ericsson.modernization.services.productcatalog.authentication;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.ericsson.modernization.services.productcatalog.model.Description;
import com.ericsson.modernization.services.productcatalog.model.EntityBase;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.model.ValidFor;

@Entity
public class SystemUser extends EntityBase implements Description, ValidFor {
	public SystemUser() {
		super();
	}
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
    @Embedded
    private TimePeriod validFor;
    private String name;
    private String description;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
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
}
