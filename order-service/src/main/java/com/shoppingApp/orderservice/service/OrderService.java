package com.shoppingApp.orderservice.service;

import com.shoppingApp.orderservice.data.dto.OrderRequest;
import com.shoppingApp.orderservice.data.dto.response.OrderResponse;

public interface OrderService {
     public OrderResponse placeOrder(OrderRequest orderRequest);
}
