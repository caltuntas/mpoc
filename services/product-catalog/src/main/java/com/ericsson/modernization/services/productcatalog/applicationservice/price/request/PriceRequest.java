package com.ericsson.modernization.services.productcatalog.applicationservice.price.request;

public class PriceRequest {

    private int id;
    private String priceType;
    private String periodType;
    private boolean isPercentage;
    private int amount;
    private String currency;
    private int chargePeriodFrom;
    private int chargePeriodTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public boolean getisPercentage() {
        return isPercentage;
    }

    public void setisPercentage(boolean percentage) {
        isPercentage = percentage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getChargePeriodFrom() {
        return chargePeriodFrom;
    }

    public void setChargePeriodFrom(int chargePeriodFrom) {
        this.chargePeriodFrom = chargePeriodFrom;
    }

    public int getChargePeriodTo() {
        return chargePeriodTo;
    }

    public void setChargePeriodTo(int chargePeriodTo) {
        this.chargePeriodTo = chargePeriodTo;
    }
}
