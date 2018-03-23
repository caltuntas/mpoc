package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class TimePeriod {
    private Date validForStartDate;
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
