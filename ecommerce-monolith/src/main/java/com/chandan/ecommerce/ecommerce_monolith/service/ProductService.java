package com.chandan.ecommerce.ecommerce_monolith.service;

import com.chandan.ecommerce.ecommerce_monolith.entity.Product;
import com.chandan.ecommerce.ecommerce_monolith.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();

    }
    public Product createProduct(Product product) {
        // Business logic: e.g., add tax? Here, just save
        // In real: Validate stock, etc.
        return productRepository.save(product);
    }
}
