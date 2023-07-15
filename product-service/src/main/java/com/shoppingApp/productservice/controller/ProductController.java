package com.shoppingApp.productservice.controller;

import com.shoppingApp.productservice.dto.ProductRequest;
import com.shoppingApp.productservice.dto.ProductResponse;
import com.shoppingApp.productservice.model.Product;
import com.shoppingApp.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
       return productService.getAllProducts();
    }
}
