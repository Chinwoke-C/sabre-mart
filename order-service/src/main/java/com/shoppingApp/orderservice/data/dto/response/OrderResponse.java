package com.shoppingApp.orderservice.data.dto.response;

import com.shoppingApp.orderservice.data.model.OrderLineItems;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String orderNumber;
    private List<OrderLineItems> orderLineItems;
}
