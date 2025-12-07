package com.chandan.ecommerce.ecommerce_monolith.service;


import com.chandan.ecommerce.ecommerce_monolith.entity.Product;
import com.chandan.ecommerce.ecommerce_monolith.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // Enables Mockito annotations
class ProductServiceTest {

    @Mock  // Creates a mock repo
    private ProductRepository productRepository;

    @InjectMocks  // Injects mock into service
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach  // Runs before each test
    void setUp() {
        sampleProduct = new Product("Test Laptop", 999.99);
    }

    @Test
    void getAllProducts_ShouldReturnListFromRepo() {
        // Arrange: Mock repo behavior
        List<Product> expected = Arrays.asList(sampleProduct);
        when(productRepository.findAll()).thenReturn(expected);

        // Act: Call service
        List<Product> actual = productService.getAllProducts();

        // Assert: Verify
        assertEquals(1, actual.size());
        assertEquals("Test Laptop", actual.get(0).getName());
        // Verify mock interaction
        // verify(productRepository).findAll();  // Uncomment to check calls
    }

    @Test
    void createProduct_ShouldSaveAndReturnProduct() {
        // Arrange: Mock save
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(1L);  // Simulate generated ID
            return p;
        });

        // Act
        Product saved = productService.createProduct(sampleProduct);

        // Assert
        assertEquals(1L, saved.getId());
        assertEquals("Test Laptop", saved.getName());
    }
}