package com.microservice.inventoryservice.controller;

import com.microservice.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{sku_code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable String sku_code){
        return inventoryService.isInStock(sku_code);
    }

}
