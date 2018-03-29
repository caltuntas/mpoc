package com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic;

import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.response.ProdSpecCharValueListModel;
import com.ericsson.modernization.services.productcatalog.applicationservice.productspeccharacteristic.response.ProdSpecCharValueUseListModel;
import com.ericsson.modernization.services.productcatalog.model.ProdSpecCharValueUse;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecCharUse;
import com.ericsson.modernization.services.productcatalog.model.ProductSpecification;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecCharUseRepository;
import com.ericsson.modernization.services.productcatalog.repository.ProductSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdSpecCharValueUseAppService {

    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private ProductSpecCharUseRepository productSpecCharUseRepository;

    public List<ProdSpecCharValueUseListModel> getProdSpecCharValueUses(int productSpecId) {

        List<ProdSpecCharValueUseListModel> prodSpecCharValueListModelList = new ArrayList<>();

        ProductSpecification specification = productSpecificationRepository.findByIdAndIsDeletedIsFalse(productSpecId);
        if (specification != null) {

            List<ProductSpecCharUse> prodSpecCharUseList = productSpecCharUseRepository.findAllByProductSpecification(specification);

            for (ProductSpecCharUse productSpecCharUse : prodSpecCharUseList) {
                List<ProdSpecCharValueUse> prodSpecCharValueUseList = productSpecCharUse.getProductSpecCharValueUses();
                ProdSpecCharValueUseListModel charValueUseListModel = new ProdSpecCharValueUseListModel();
                charValueUseListModel.setProdSpecCharUseId(productSpecCharUse.getId());

                for (ProdSpecCharValueUse prodSpecCharValueUse : prodSpecCharValueUseList) {

                    charValueUseListModel.getProdSpecCharValueList().add(createCharValueListModel(prodSpecCharValueUse));
                    charValueUseListModel.setProdSpecCharDescription(
                            prodSpecCharValueUse.getProductSpecCharacteristicValue().getProductSpecCharacteristic().getName());
                    charValueUseListModel.setProdSpecCharId(
                            prodSpecCharValueUse.getProductSpecCharacteristicValue().getProductSpecCharacteristic().getId());
                }

                prodSpecCharValueListModelList.add(charValueUseListModel);
            }
        }

        return prodSpecCharValueListModelList;
    }

    private ProdSpecCharValueListModel createCharValueListModel(ProdSpecCharValueUse prodSpecCharValueUse) {
        ProdSpecCharValueListModel charValueListModel = new ProdSpecCharValueListModel();

        charValueListModel.setProdSpecCharValueId(prodSpecCharValueUse.getId());

        charValueListModel.setProdSpecCharValue(
                prodSpecCharValueUse.getProductSpecCharacteristicValue().getValue());

        charValueListModel.setProdSpecCharValueUseId(
                prodSpecCharValueUse.getId());

        return charValueListModel;
    }

}
