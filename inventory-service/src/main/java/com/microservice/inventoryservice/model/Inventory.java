package com.microservice.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue()
    private Long id;
    private String skuCode;
    private Integer quantity;
}
