package com.ericsson.modernization.services.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class TimePeriod {
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validForStartDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validForEndDate;

    public Date getValidForStartDate() {
        return validForStartDate;
    }

    public void setValidForStartDate(Date validForStartDate) {
        this.validForStartDate = validForStartDate;
    }

    public Date getValidForEndDate() {
        return validForEndDate;
    }

    public void setValidForEndDate(Date validForEndDate) {
        this.validForEndDate = validForEndDate;
    }
}
