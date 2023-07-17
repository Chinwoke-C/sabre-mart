package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.service.data.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
