package com.shoppingApp.orderservice.controller;

import com.shoppingApp.orderservice.data.dto.OrderRequest;
import com.shoppingApp.orderservice.data.dto.response.OrderResponse;
import com.shoppingApp.orderservice.data.model.Order;
import com.shoppingApp.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/create-order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(orderRequest));
    }
}
