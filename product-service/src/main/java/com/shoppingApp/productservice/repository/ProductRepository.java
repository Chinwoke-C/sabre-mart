package com.shoppingApp.productservice.repository;

import com.shoppingApp.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
