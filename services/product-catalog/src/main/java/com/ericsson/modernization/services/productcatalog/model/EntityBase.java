package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int createUserId;
    private Date createUserDate;
    private int updateUserId;
    private Date updateUserDate;
    private boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateUserDate() {
        return createUserDate;
    }

    public void setCreateUserDate(Date createUserDate) {
        this.createUserDate = createUserDate;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateUserDate() {
        return updateUserDate;
    }

    public void setUpdateUserDate(Date updateUserDate) {
        this.updateUserDate = updateUserDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
