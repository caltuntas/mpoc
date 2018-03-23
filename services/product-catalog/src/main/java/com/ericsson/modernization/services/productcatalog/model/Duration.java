package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.Embeddable;

@Embeddable
public class Duration {
    private long periodValue;
    private int periodUnit;

    public long getPeriodValue() {
        return periodValue;
    }

    public void setPeriodValue(long periodValue) {
        this.periodValue = periodValue;
    }

    public int getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(int periodUnit) {
        this.periodUnit = periodUnit;
    }
}
