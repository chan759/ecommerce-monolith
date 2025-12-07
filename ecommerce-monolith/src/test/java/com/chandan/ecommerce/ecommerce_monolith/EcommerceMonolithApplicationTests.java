package com.chandan.ecommerce.ecommerce_monolith;

import com.chandan.ecommerce.ecommerce_monolith.entity.Product;
import com.chandan.ecommerce.ecommerce_monolith.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class EcommerceMonolithApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void createAndFetchProducts(){
		Product product = new Product("Integration Testing",12.9);
		productRepository.save(product);

		//when
		Product saved = productRepository.findById(product.getId()).orElse(null);

		//then
		assertThat(saved).isNotNull();
		assertThat(saved.getName()).isEqualTo("Integration Testing");


	}

}
