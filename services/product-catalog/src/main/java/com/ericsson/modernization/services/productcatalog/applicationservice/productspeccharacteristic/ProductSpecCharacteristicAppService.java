package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic;

import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.request.ProductSpecCharacteristicCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.request.ProductSpecCharacteristicEditRequest;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristic;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharacteristicValue;
import com.ericsson.modernization.services.productcatalog.model.TimePeriod;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecCharacteristicRepository;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecCharacteristicValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductSpecCharacteristicAppService {

    @Autowired
    private ProductSpecCharacteristicRepository productSpecCharacteristicRepository;
    @Autowired
    private ProductSpecCharacteristicValueRepository productSpecCharacteristicValueRepository;

    public ProductSpecCharacteristic create(ProductSpecCharacteristicCreateRequest productSpecCharacteristicCreateRequest) {
        ProductSpecCharacteristic productSpecCharacteristic = new ProductSpecCharacteristic();
        productSpecCharacteristic.setName(productSpecCharacteristicCreateRequest.getName());
        productSpecCharacteristic.setDescription(productSpecCharacteristicCreateRequest.getDescription());
        productSpecCharacteristic.setValueType(productSpecCharacteristicCreateRequest.getValueType());
        productSpecCharacteristic.setisRequired(productSpecCharacteristicCreateRequest.getisRequired());
        productSpecCharacteristic.setisConfigurable(productSpecCharacteristicCreateRequest.getisConfigurable());

        Calendar c = Calendar.getInstance();
        TimePeriod validFor = new TimePeriod();
        validFor.setValidForStartDate(c.getTime());
        c.add(Calendar.MONTH,1);
        validFor.setValidForEndDate(c.getTime());
        productSpecCharacteristic.setValidFor(validFor);

        productSpecCharacteristicRepository.save(productSpecCharacteristic);

        if(productSpecCharacteristic.getValueType() == 1) {

            List<String> items = Arrays.asList(productSpecCharacteristicCreateRequest.getCharValueString().trim().split("\\s*,\\s*"));
            ArrayList<ProductSpecCharacteristicValue> pscvList = new ArrayList<ProductSpecCharacteristicValue>();

            for (String item : items) {
                ProductSpecCharacteristicValue pscv = new ProductSpecCharacteristicValue();
                pscv.setValue(item);
                pscv.setProductSpecCharacteristic(productSpecCharacteristic);
                productSpecCharacteristicValueRepository.save(pscv);
            }
        }

        return productSpecCharacteristic;
    }

    public void delete(int productSpecCharacteristicID){
        ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicRepository.findById(productSpecCharacteristicID).get();
        if(productSpecCharacteristic != null) {
/*            Date _date = new Date();
            productSpecCharacteristic.getValidFor().setValidForEndDate(_date);*/
            productSpecCharacteristic.setDeleted(true);
            productSpecCharacteristicRepository.save(productSpecCharacteristic);
        }
    }

    public ProductSpecCharacteristic findById(int id){
        return productSpecCharacteristicRepository.findByIdAndIsDeletedIsFalse(id);
    }

    public ProductSpecCharacteristicEditRequest findByIdForEditing(int id){
        ProductSpecCharacteristicEditRequest productSpecCharacteristicEditRequest = new ProductSpecCharacteristicEditRequest();
        ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicRepository.findByIdAndIsDeletedIsFalse(id);

        productSpecCharacteristicEditRequest.setID(productSpecCharacteristic.getId());
        productSpecCharacteristicEditRequest.setName(productSpecCharacteristic.getName());
        productSpecCharacteristicEditRequest.setDescription(productSpecCharacteristic.getDescription());
        productSpecCharacteristicEditRequest.setValidFor(productSpecCharacteristic.getValidFor());
        productSpecCharacteristicEditRequest.setValueType(productSpecCharacteristic.getValueType());
        productSpecCharacteristicEditRequest.setisConfigurable(productSpecCharacteristic.getisConfigurable());
        productSpecCharacteristicEditRequest.setisRequired(productSpecCharacteristic.getisRequired());

        if (productSpecCharacteristicEditRequest.getValueType() == 1) {
            List<ProductSpecCharacteristicValue> productSpecCharacteristicValueList = productSpecCharacteristic.getProductSpecCharacteristicValues();
            List<String> values = new ArrayList<String>();

            for (ProductSpecCharacteristicValue pscv : productSpecCharacteristicValueList) {
                if (!pscv.isDeleted()) {
                    values.add(pscv.getValue());
                }
            }

            String csv = String.join(",", values);
            productSpecCharacteristicEditRequest.setCharValueString(csv);
        }

        return productSpecCharacteristicEditRequest;
    }

    public List<ProductSpecCharacteristic> findAll() {
        return productSpecCharacteristicRepository.findAllByIsDeletedIsFalse();
    }

    public ProductSpecCharacteristic updateCharacteristic(ProductSpecCharacteristicEditRequest productSpecCharacteristicEditRequest) {
        ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicRepository.findByIdAndIsDeletedIsFalse(productSpecCharacteristicEditRequest.getID());

        List<ProductSpecCharacteristicValue> pscvList = productSpecCharacteristicValueRepository.findByProductSpecCharacteristicEquals(productSpecCharacteristic);
        for (ProductSpecCharacteristicValue pscv: pscvList) {
            pscv.setDeleted(true);
        }

        productSpecCharacteristic.setName(productSpecCharacteristicEditRequest.getName());
        productSpecCharacteristic.setDescription(productSpecCharacteristicEditRequest.getDescription());
        productSpecCharacteristic.setValidFor(productSpecCharacteristicEditRequest.getValidFor());
        productSpecCharacteristic.setValueType(productSpecCharacteristicEditRequest.getValueType());
        productSpecCharacteristic.setisConfigurable(productSpecCharacteristicEditRequest.getisConfigurable());
        productSpecCharacteristic.setisRequired(productSpecCharacteristicEditRequest.getisRequired());

        productSpecCharacteristicRepository.save(productSpecCharacteristic);


        if (productSpecCharacteristic.getValueType() == 1) {
            List<String> items = Arrays.asList(productSpecCharacteristicEditRequest.getCharValueString().split("\\s*,\\s*"));

            for (String item : items) {
                ProductSpecCharacteristicValue pscv = productSpecCharacteristicValueRepository.findByValueAndProductSpecCharacteristicEquals(item, productSpecCharacteristic);
                if (pscv == null) {
                    pscv = new ProductSpecCharacteristicValue();
                    pscv.setValue(item);
                    pscv.setProductSpecCharacteristic(productSpecCharacteristic);
                } else {
                    pscv.setDeleted(false);
                }
                productSpecCharacteristicValueRepository.save(pscv);
            }
        }

        return productSpecCharacteristic;
    }
}
