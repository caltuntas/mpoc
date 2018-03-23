package com.ericsson.modernization.services.productcatalog.model;

import java.math.BigDecimal;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class ComponentProductOfferPrice extends ProductOfferingPrice {
    @ManyToOne
    private AmountType amountType;
    private BigDecimal percentage;
    @ManyToOne
    private MonetaryAmount amount;

    @ManyToOne
    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }


}
