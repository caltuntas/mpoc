package com.ericsson.modernization.services.productcatalog.applicationservice.productspecification;

import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.request.ProductSpecificationCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.request.ProductSpecificationValueItemModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response.ProdSpecCharListResponse;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response.ProdSpecCharValueModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response.ProductSpecDetailForEditResponse;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspecification.response.ProductSpecListModel;
import com.ericsson.modernization.services.productcatalog.model.*;
import com.ericsson.modernization.services.productcatalog.repository.*;

import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductSpecificationAppService {
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private ProductSpecCharacteristicRepository characteristicRepository;
    @Autowired
    private ProductSpecCharacteristicValueRepository characteristicValueRepository;
    @Autowired
    ProductSpecCharUseRepository charUseRepository;
    @Autowired
    ProdSpecCharValueUseRepository charValueUseRepository;

    public void create(ProductSpecificationCreateRequest request) {

        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setName(request.name);
        productSpecification.setCode(request.code);
        productSpecification.setDescription(request.description);
        // productSpecification.setProductType(request.productType);
        //productSpecification.setStatus(request.status);
        productSpecification.setCreateUserDate(new Date());
        productSpecificationRepository.save(productSpecification);

        for (ProductSpecificationValueItemModel selectedChar : request.selectedCharacteristics) {
            ProductSpecCharacteristic characteristic = characteristicRepository.findByIdAndIsDeletedIsFalse(selectedChar.id);
            ProductSpecCharUse charuse = new ProductSpecCharUse();
            charuse.setProductSpecification(productSpecification);
            charuse.setProductSpecCharacteristic(characteristic);
            charuse.setCreateUserDate(new Date());
            charuse.setCharacteristicType(1);
            charuse.setUpdateUserDate(new Date());

            charUseRepository.save(charuse);

            for (int selectedValue : selectedChar.selectedValueIds) {

                ProductSpecCharacteristicValue val = characteristicValueRepository.findById(selectedValue);
                ProdSpecCharValueUse valueUse = new ProdSpecCharValueUse();

                valueUse.setProductSpecCharacteristicValue(val);
                valueUse.setProductSpecCharUse(charuse);
                charValueUseRepository.save((valueUse));
            }
        }

    }

    public List<ProdSpecCharListResponse> getCharacteristics() {
        return characteristicRepository.findAllByIsDeletedIsFalse().stream()
                .map(x -> new ProdSpecCharListResponse(x.getId(), x.getName(), x.getValueType(), x.getProductSpecCharacteristicValues().stream()
                        .map(y -> new ProdSpecCharValueModel(y.getId(), y.getValue())).
                                collect(Collectors.toList())
                )).collect(Collectors.toList());
    }


    public List<ProductSpecListModel> getSpecs() {
        return productSpecificationRepository.findAll().stream()
                .map(x -> new ProductSpecListModel(x.getId(), x.getName(), x.getCode(), x.getDescription(), x.getCreateUserDate())).collect(Collectors.toList());


    }

    public ProductSpecification findById(int id) {
        return productSpecificationRepository.findByIdAndIsDeletedIsFalse(id);

    }

    public ProductSpecDetailForEditResponse getSpecForEdit(int id) {
        ProductSpecification spec = productSpecificationRepository.findById(id).get();
        ProductSpecDetailForEditResponse model = new ProductSpecDetailForEditResponse();
        model.id = spec.getId();
        model.name = spec.getName();
        model.code = spec.getCode();
        model.description = spec.getDescription();
        model.selectedCharacteristics = spec.getProductSpecCharUses().stream()
                .map(x -> new ProductSpecificationValueItemModel(x.getProductSpecCharacteristic().getId(),
                        x.getProductSpecCharValueUses().stream().map(y -> y.getProductSpecCharacteristicValue().getId()).collect(Collectors.toList()))).collect(Collectors.toList());
        return model;
    }

}

