package com.chandan.ecommerce.ecommerce_monolith.repository;

import com.chandan.ecommerce.ecommerce_monolith.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
