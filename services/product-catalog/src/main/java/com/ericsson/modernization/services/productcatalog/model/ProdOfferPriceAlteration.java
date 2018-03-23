package com.ericsson.modernization.services.productcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class ProdOfferPriceAlteration extends ComponentProductOfferPrice {

    public ProdOfferPriceAlteration() {
        charges = new ArrayList<ProdOfferPriceCharge>();
    }

    @Transient  //TODO: karşılıklı List<A> List<B> ilişkisi sorgulanacak. manyToMany için genelde ara tablo kullanılır.
    private List<ProdOfferPriceCharge> charges;

    public List<ProdOfferPriceCharge> getCharges() {
        return charges;
    }

    public void setCharges(List<ProdOfferPriceCharge> charges) {
        this.charges = charges;
    }


}
