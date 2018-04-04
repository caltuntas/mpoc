package com.ericsson.modernization.services.productcatalog.applicationservice.price;

import com.ericsson.modernization.services.productcatalog.applicationservice.price.request.PriceRequest;
import com.ericsson.modernization.services.productcatalog.model.*;
import com.ericsson.modernization.services.productcatalog.repository.DiscountProdOfferPriceAlterationRepository;
import com.ericsson.modernization.services.productcatalog.repository.OneTimeProdOfferPriceChargeRepository;
import com.ericsson.modernization.services.productcatalog.repository.ProductOfferingPriceRepository;
import com.ericsson.modernization.services.productcatalog.repository.RecurringProdOfferPriceChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceAppService {

    @Autowired
    private OneTimeProdOfferPriceChargeRepository oneTimeProdOfferPriceChargeRepository;
    @Autowired
    private RecurringProdOfferPriceChargeRepository recurringProdOfferPriceChargeRepository;
    @Autowired
    private DiscountProdOfferPriceAlterationRepository discountProdOfferPriceAlterationRepository;
    @Autowired
    private ProductOfferingPriceRepository productOfferingPriceRepository;

    public void create(List<PriceRequest> priceRequestList, ProductOffering productOffering) {
        for (PriceRequest pr: priceRequestList) {
            createMappinng(pr, productOffering);
        }
    }

    public List<PriceRequest> getAllPrices(ProductOffering productOffering) {
        List<ProductOfferingPrice> productOfferingPriceList = productOfferingPriceRepository.findAllByProductOfferingAndIsDeletedIsFalse(productOffering);
        List<PriceRequest> priceRequestsList = new ArrayList<>();

        for (ProductOfferingPrice por: productOfferingPriceList) {
            PriceRequest pr = new PriceRequest();

            if(por instanceof OneTimeProdOfferPriceCharge) {
                pr.setPriceType("OneTime");
            } else if (por instanceof RecurringProdOfferPriceCharge) {
                pr.setPriceType("Recurring");
                pr.setPeriodType(((RecurringProdOfferPriceCharge) por).getPeriodType());
            } else if (por instanceof DiscountProdOfferPriceAlteration) {
                pr.setPriceType("Discount");
                pr.setPeriodType(((DiscountProdOfferPriceAlteration) por).getPeriodType());
                pr.setisPercentage(((DiscountProdOfferPriceAlteration) por).getisPercentage());
                pr.setChargePeriodFrom(((DiscountProdOfferPriceAlteration) por).getChargePeriodFrom());
                pr.setChargePeriodTo(((DiscountProdOfferPriceAlteration) por).getChargePeriodTo());
            } else {
                //TO-DO: exceptional cases must be considered
            }

            pr.setAmount(((ComponentProductOfferPrice) por).getAmount());
            pr.setCurrency(((ComponentProductOfferPrice) por).getCurrency());

            priceRequestsList.add(pr);
        }
        return priceRequestsList;
    }

    public void updatePrices(List<PriceRequest> priceRequestList, ProductOffering productOffering) {
        List<ProductOfferingPrice> productOfferingPriceList = productOfferingPriceRepository.findAllByProductOfferingAndIsDeletedIsFalse(productOffering);

        for (ProductOfferingPrice por: productOfferingPriceList) {
            por.setDeleted(true);
        }

        for (PriceRequest pr: priceRequestList) {
            ProductOfferingPrice por = productOfferingPriceRepository.findById(pr.getId()).get();
            if(por != null) {
                por.setDeleted(false);
                if(pr.getPriceType().equals("OneTime")) {
                    OneTimeProdOfferPriceCharge oneTimeProdOfferPriceCharge = new OneTimeProdOfferPriceCharge();
                    oneTimeProdOfferPriceCharge.setAmount(pr.getAmount());
                    oneTimeProdOfferPriceCharge.setCurrency(pr.getCurrency());
                    oneTimeProdOfferPriceChargeRepository.save(oneTimeProdOfferPriceCharge);
                } else if(pr.getPriceType().equals("Recurring")) {
                    RecurringProdOfferPriceCharge recurringProdOfferPriceCharge = new RecurringProdOfferPriceCharge();
                    recurringProdOfferPriceCharge.setPeriodType(pr.getPeriodType());
                    recurringProdOfferPriceCharge.setAmount(pr.getAmount());
                    recurringProdOfferPriceCharge.setCurrency(pr.getCurrency());
                    recurringProdOfferPriceChargeRepository.save(recurringProdOfferPriceCharge);
                } else if(pr.getPriceType().equals("Discount")) {
                    DiscountProdOfferPriceAlteration discountProdOfferPriceAlteration = new DiscountProdOfferPriceAlteration();
                    discountProdOfferPriceAlteration.setPeriodType(pr.getPeriodType());
                    discountProdOfferPriceAlteration.setisPercentage(pr.getisPercentage());
                    discountProdOfferPriceAlteration.setAmount(pr.getAmount());
                    discountProdOfferPriceAlteration.setCurrency(pr.getCurrency());
                    discountProdOfferPriceAlteration.setChargePeriodFrom(pr.getChargePeriodFrom());
                    discountProdOfferPriceAlteration.setChargePeriodTo(pr.getChargePeriodTo());
                    discountProdOfferPriceAlterationRepository.save(discountProdOfferPriceAlteration);
                } else {
                    //TO-DO: exceptional cases must be considered
                }
            } else {
                createMappinng(pr,productOffering);
            }
        }
    }


    public void createMappinng(PriceRequest _pr, ProductOffering _productOffering) {
        if(_pr.getPriceType().equals("OneTime")) {
            OneTimeProdOfferPriceCharge oneTimeProdOfferPriceCharge = new OneTimeProdOfferPriceCharge();
            oneTimeProdOfferPriceCharge.setAmount(_pr.getAmount());
            oneTimeProdOfferPriceCharge.setCurrency(_pr.getCurrency());
            oneTimeProdOfferPriceCharge.setProductOffering(_productOffering);
            oneTimeProdOfferPriceChargeRepository.save(oneTimeProdOfferPriceCharge);
        } else if(_pr.getPriceType().equals("Recurring")) {
            RecurringProdOfferPriceCharge recurringProdOfferPriceCharge = new RecurringProdOfferPriceCharge();
            recurringProdOfferPriceCharge.setPeriodType(_pr.getPeriodType());
            recurringProdOfferPriceCharge.setAmount(_pr.getAmount());
            recurringProdOfferPriceCharge.setCurrency(_pr.getCurrency());
            recurringProdOfferPriceCharge.setProductOffering(_productOffering);
            recurringProdOfferPriceChargeRepository.save(recurringProdOfferPriceCharge);
        } else if(_pr.getPriceType().equals("Discount")) {
            DiscountProdOfferPriceAlteration discountProdOfferPriceAlteration = new DiscountProdOfferPriceAlteration();
            discountProdOfferPriceAlteration.setPeriodType(_pr.getPeriodType());
            discountProdOfferPriceAlteration.setisPercentage(_pr.getisPercentage());
            discountProdOfferPriceAlteration.setAmount(_pr.getAmount());
            discountProdOfferPriceAlteration.setCurrency(_pr.getCurrency());
            discountProdOfferPriceAlteration.setChargePeriodFrom(_pr.getChargePeriodFrom());
            discountProdOfferPriceAlteration.setChargePeriodTo(_pr.getChargePeriodTo());
            discountProdOfferPriceAlteration.setProductOffering(_productOffering);
            discountProdOfferPriceAlterationRepository.save(discountProdOfferPriceAlteration);
        } else {
            //TO-DO: exceptional cases must be considered
        }
    }
}























