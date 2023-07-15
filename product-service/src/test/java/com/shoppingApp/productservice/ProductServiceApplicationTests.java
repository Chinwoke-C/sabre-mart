package com.shoppingApp.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingApp.productservice.dto.ProductRequest;
import com.shoppingApp.productservice.model.Product;
import com.shoppingApp.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectmapper;
	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry){
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}


	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest request = getProductRequest();
		String productRequestString = objectmapper.writeValueAsString(request);
			mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
		assertEquals(1, productRepository.findAll().size());
	}
	@Test
	void shouldGetAllProduct() throws Exception {
		List<Product> products = getAllProducts();
		mockMvc.perform((MockMvcRequestBuilders.get("/api/v1/product/all"))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status(). isOk());
				assertEquals(3, productRepository.findAll().size());

	}

	private List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("1","airpods 5", "boundless sound", BigDecimal.valueOf(1300)));
		products.add(new Product("2","jordan 8", "jump higher", BigDecimal.valueOf(700)));
		products.add(new Product("3","hoodie", "chill not", BigDecimal.valueOf(300)));
		productRepository.saveAll(products);

		return products;
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("airpods 6")
				.description("boundless sound")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}
