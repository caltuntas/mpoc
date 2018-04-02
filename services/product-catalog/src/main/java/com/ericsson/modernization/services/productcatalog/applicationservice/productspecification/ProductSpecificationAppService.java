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
import org.aspectj.weaver.ast.Var;
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


    public List<ProdSpecCharListResponse> getCharacteristics() {
        return characteristicRepository.findAllByIsDeletedIsFalse().stream().filter(x -> x.isDeleted() == false)
                .map(x -> new ProdSpecCharListResponse(x.getId(), x.getName(), x.getValueType(), x.getProductSpecCharacteristicValues().stream().filter(y -> y.isDeleted() == false)
                        .map(y -> new ProdSpecCharValueModel(y.getId(), y.getValue())).
                                collect(Collectors.toList())
                )).collect(Collectors.toList());
    }

    public void create(ProductSpecificationCreateRequest request) {

        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setName(request.name);
        productSpecification.setCode(request.code);
        productSpecification.setDescription(request.description);
        productSpecification.setCreateUserDate(new Date());
        productSpecificationRepository.save(productSpecification);

        for (ProductSpecificationValueItemModel selectedChar : request.selectedCharacteristics) {
            ProductSpecCharacteristic characteristic = characteristicRepository.findByIdAndIsDeletedIsFalse(selectedChar.id);
            ProductSpecCharUse charuse = new ProductSpecCharUse();
            charuse.setProductSpecification(productSpecification);
            charuse.setProductSpecCharacteristic(characteristic);
            charuse.setCreateUserDate(new Date());
            charuse.setCharacteristicType(1);
            charuse.setVersionNumber(1);
            charuse.setUpdateUserDate(new Date());
            charuse.setDeleted(false);


            charUseRepository.save(charuse);

            for (int selectedValue : selectedChar.selectedValueIds) {

                ProductSpecCharacteristicValue val = characteristicValueRepository.findById(selectedValue);
                ProdSpecCharValueUse valueUse = new ProdSpecCharValueUse();

                valueUse.setProductSpecCharacteristicValue(val);
                valueUse.setProductSpecCharUse(charuse);
                valueUse.setDeleted(false);
                charValueUseRepository.save((valueUse));
            }
        }

    }

    public void Update(ProductSpecDetailForEditResponse request) {

        ProductSpecification productSpecification = productSpecificationRepository.findById(request.id).get();
        productSpecification.setName(request.name);
        productSpecification.setCode(request.code);
        productSpecification.setDescription(request.description);
        productSpecification.setUpdateUserDate(new Date());
        productSpecificationRepository.save(productSpecification);

        for (ProductSpecCharUse charuse : productSpecification.getProductSpecCharUses()) {
            charuse.setDeleted(true);
            for (ProdSpecCharValueUse valueUse : charuse.getProductSpecCharValueUses()) {
                valueUse.setDeleted(true);
                charValueUseRepository.save(valueUse);
            }
            charUseRepository.save(charuse);
        }
        final ProductSpecification productSpecification2 = productSpecification;

        for (ProductSpecificationValueItemModel selectedChar : request.selectedCharacteristics) {

            ProductSpecCharUse charUse = charUseRepository.findByProductSpecificationAndProductSpecCharacteristic_Id(productSpecification2,selectedChar.id);

            if (charUse != null) {
                charUse.setDeleted(false);
                charUse.setUpdateUserDate(new Date());

            } else {
                ProductSpecCharacteristic characteristic = characteristicRepository.findByIdAndIsDeletedIsFalse(selectedChar.id);
                charUse = new ProductSpecCharUse();
                charUse.setProductSpecification(productSpecification);
                charUse.setProductSpecCharacteristic(characteristic);
                charUse.setCreateUserDate(new Date());
                charUse.setCharacteristicType(1);
                charUse.setVersionNumber(1);
                charUse.setUpdateUserDate(new Date());
            }

            charUseRepository.save(charUse);
            final ProductSpecCharUse charuse2 = charUse;

            for (int selectedValue : selectedChar.selectedValueIds) {

                ProductSpecCharacteristicValue productSpecCharacteristicValue = characteristicValueRepository.findById(selectedValue);

                ProdSpecCharValueUse valueUse = charValueUseRepository.findByProductSpecCharacteristicValueAndProductSpecCharUse(productSpecCharacteristicValue, charuse2);
                if (valueUse != null) {
                    valueUse.setDeleted(false);
                    valueUse.setUpdateUserDate(new Date());
                } else {
                    ProductSpecCharacteristicValue val = characteristicValueRepository.findById(selectedValue);
                    valueUse = new ProdSpecCharValueUse();
                    valueUse.setProductSpecCharacteristicValue(val);
                    valueUse.setProductSpecCharUse(charUse);

                }

                charValueUseRepository.save(valueUse);
            }
        }
    }

    public void delete(int id) {
        ProductSpecification productSpecification = productSpecificationRepository.findById(id).get();
        if (productSpecification != null) {
            productSpecification.setDeleted(true);
            productSpecificationRepository.save(productSpecification);
        }

    }

    public List<ProductSpecListModel> getSpecs() {
        return productSpecificationRepository.findAllByIsDeletedIsFalse().stream()
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
        model.selectedCharacteristics = spec.getProductSpecCharUses().stream().filter(x -> x.isDeleted() == false)
                .map(x -> new ProductSpecificationValueItemModel(x.getProductSpecCharacteristic().getId(),
                        x.getProductSpecCharValueUses().stream().filter(y -> y.isDeleted() == false).map(y -> y.getProductSpecCharacteristicValue().getId()).collect(Collectors.toList()))).collect(Collectors.toList());
        return model;
    }


}

