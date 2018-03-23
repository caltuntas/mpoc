package com.ericsson.modernization.services.productcatalog;

import com.ericsson.modernization.services.productcatalog.applicationservice.request.ProductOfferingCreateRequest;
import com.ericsson.modernization.services.productcatalog.model.ProductOffering;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOfferingAppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductOfferingServiceTests {

    @Autowired
    private ProductOfferingAppService productOfferingAppService;

    @Test
    public void createOffering() {
        ProductOfferingCreateRequest createRequest = new ProductOfferingCreateRequest();
        createRequest.setName("test-offering");
        createRequest.setDescription("test-desc");
        createRequest.setSellable(true);
        productOfferingAppService.create(createRequest);
    }

}
