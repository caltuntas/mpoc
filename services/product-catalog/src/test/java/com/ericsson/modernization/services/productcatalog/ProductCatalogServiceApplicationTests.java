package com.ericsson.modernization.services.productcatalog;

        import com.ericsson.modernization.services.productcatalog.applicationservice.Catalog.CatalogAppService;
        import com.ericsson.modernization.services.productcatalog.applicationservice.Catalog.request.CatalogCreateRequest;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCatalogServiceApplicationTests {


    @Autowired
    private CatalogAppService catalogAppService;

    @Test
    public void createCatalog() {
        CatalogCreateRequest createRequest = new CatalogCreateRequest();
        createRequest.setName("HM Catalog Test 1");
        createRequest.setDescription("Test instance record");
        createRequest.setReplicated(false);

        catalogAppService.create(createRequest);
    }
}
