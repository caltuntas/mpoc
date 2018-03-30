package com.ericsson.modernization.services.productcatalog.category;
/*
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ericsson.modernization.services.productcatalog.applicationservice.category.CategoryService;
import com.ericsson.modernization.services.productcatalog.rest.category.CategoryRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryRestController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.findAllWithModel()).thenReturn(null);
        this.mockMvc.perform(get("/categories")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("categories")));
    }
}*/