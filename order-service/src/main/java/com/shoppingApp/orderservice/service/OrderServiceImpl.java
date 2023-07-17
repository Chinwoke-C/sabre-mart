package com.shoppingApp.orderservice.service;

import com.shoppingApp.orderservice.data.dto.OrderLineItemsDto;
import com.shoppingApp.orderservice.data.dto.OrderRequest;
import com.shoppingApp.orderservice.data.dto.response.InventoryResponse;
import com.shoppingApp.orderservice.data.dto.response.OrderResponse;
import com.shoppingApp.orderservice.data.model.Order;
import com.shoppingApp.orderservice.data.model.OrderLineItems;
import com.shoppingApp.orderservice.data.repository.OrderRepository;
import com.shoppingApp.orderservice.exception.ItemNotInStockException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final WebClient webClient;
    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto()
                .stream()
                .map(this::MapToDto)
                .toList();
        order.setOrderlineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderlineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();


        // Call Inventory Service, and place order if product is in stock
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponses)
                    .allMatch(InventoryResponse::isInStock);
        try{
            if (allProductsInStock) {
                orderRepository.save(order);
                return OrderResponse.builder()
                        .orderLineItems(order.getOrderlineItemsList())
                        .message("Order placed successfully")
                        .build();
            }

        } catch (RuntimeException e){
            throw new ItemNotInStockException(e.getMessage());
        }

        return OrderResponse.builder()
                .message("product not in stock, try again later")
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
