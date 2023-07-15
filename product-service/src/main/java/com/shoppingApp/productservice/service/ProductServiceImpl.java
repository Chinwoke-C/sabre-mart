package com.shoppingApp.productservice.service;

import com.shoppingApp.productservice.dto.ProductRequest;
import com.shoppingApp.productservice.dto.ProductResponse;
import com.shoppingApp.productservice.model.Product;
import com.shoppingApp.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements  ProductService{
    private final ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
         productRepository.save(product);
         log.info("product {} is saved", product.getId());

    }

    @Override
    public List<ProductResponse> getAllProducts() {
       List<Product> products =  productRepository.findAll();
     return  products.stream().map(product-> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
