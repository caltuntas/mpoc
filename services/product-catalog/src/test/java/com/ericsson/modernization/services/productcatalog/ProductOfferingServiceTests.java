package com.ericsson.modernization.services.productcatalog;

import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.Request.ProductOfferingCreateRequest;
import com.ericsson.modernization.services.productcatalog.applicationservice.ProductOffering.ProductOfferingAppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
