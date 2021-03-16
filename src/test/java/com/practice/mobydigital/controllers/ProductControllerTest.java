package com.practice.mobydigital.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.mobydigital.models.Dtos.AddProductDto;
import com.practice.mobydigital.models.Dtos.UpdateProductDto;
import com.practice.mobydigital.models.Product;
import com.practice.mobydigital.models.responses.ProductResponse;
import com.practice.mobydigital.services.ProductService;
import com.practice.mobydigital.utils.FactoryObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest implements FactoryObject {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Before
    public void openMocks() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void addProductTest() throws Exception {

        AddProductDto productDto = createAddProductDto();
        Product product = createProduct();
        String jsonRequest = createJsonRequest(productDto);

        when(productService.add(productDto)).thenReturn(product);
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
    }

    @Test
    public void getProductsTest() throws Exception {

        List<ProductResponse> productResponse = Collections.singletonList(createProductResponse());
        when(productService.products()).thenReturn(productResponse);
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void removeTest() throws Exception {

        String productResponse = new ObjectMapper().writeValueAsString(createProductResponse());

        when(productService.delete("12")).thenReturn(createProduct());
        MvcResult mvcResult = mockMvc.perform(delete("/{product-id}", "12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(productResponse, response);
    }

    @Test
    public void testPartialUpdateOk() throws Exception {

        UpdateProductDto productDto = createUpdateProductDto();
        Product product = createProduct();
        String productResponse = new ObjectMapper().writeValueAsString(createProductResponse());

        String jsonRequest = createJsonRequest(productDto);

        when(productService.update("12", productDto)).thenReturn(product);

        MvcResult mvcResult = mockMvc.perform(patch("/{product-id}", "12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(productResponse, response);
    }
}
