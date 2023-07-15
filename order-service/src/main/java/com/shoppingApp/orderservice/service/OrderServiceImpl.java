package com.shoppingApp.orderservice.service;

import com.shoppingApp.orderservice.data.dto.OrderLineItemsDto;
import com.shoppingApp.orderservice.data.dto.OrderRequest;
import com.shoppingApp.orderservice.data.dto.response.OrderResponse;
import com.shoppingApp.orderservice.data.model.Order;
import com.shoppingApp.orderservice.data.model.OrderLineItems;
import com.shoppingApp.orderservice.data.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems =orderRequest.getOrderLineItemsDto()
                .stream()
                .map(this::MapToDto)
                .toList();
        order.setOrderlineItemsList(orderLineItems);

        orderRepository.save(order);
        return OrderResponse.builder()
                .orderLineItems(order.getOrderlineItemsList())
                .build();
    }

    private OrderLineItems MapToDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
