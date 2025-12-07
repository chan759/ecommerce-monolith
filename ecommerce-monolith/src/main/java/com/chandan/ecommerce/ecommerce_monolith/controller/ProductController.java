package com.chandan.ecommerce.ecommerce_monolith.controller;

import com.chandan.ecommerce.ecommerce_monolith.entity.Product;
import com.chandan.ecommerce.ecommerce_monolith.repository.ProductRepository;
import com.chandan.ecommerce.ecommerce_monolith.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
}
