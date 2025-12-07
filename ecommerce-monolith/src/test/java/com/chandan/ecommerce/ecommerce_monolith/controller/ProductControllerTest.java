package com.chandan.ecommerce.ecommerce_monolith.controller;

import com.chandan.ecommerce.ecommerce_monolith.entity.Product;
import com.chandan.ecommerce.ecommerce_monolith.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp(){
        sampleProduct = new Product("Test Phone",23.2);
    }

    @Test
    void getAllProducts_ShouldReturnJsonList() throws Exception{
        //arrange
        List<Product> products = Arrays.asList(sampleProduct);
        when(productService.getAllProducts()).thenReturn(products);

        //act & assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Test Phone"));

    }

    @Test
    void createProduct_WithValidData_ShouldReturnCreated() throws Exception{
        sampleProduct.setId(1L);
        when(productService.createProduct(any(Product.class))).thenReturn(sampleProduct);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Phone"));
    }


}
