package com.shoppingApp.orderservice.data.repository;

import com.shoppingApp.orderservice.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
