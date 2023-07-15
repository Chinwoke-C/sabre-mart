package com.shoppingApp.productservice.service;

import com.shoppingApp.productservice.dto.ProductRequest;
import com.shoppingApp.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
     void createProduct(ProductRequest productRequest);
     List<ProductResponse> getAllProducts();

}
