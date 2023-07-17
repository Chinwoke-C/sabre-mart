package com.shoppingApp.orderservice.exception;

public class ItemNotInStockException extends OrderException{

    public ItemNotInStockException(){
        this("Product not in stock, please try again later");
    }
    public ItemNotInStockException(String message) {
        super(message);
    }
}
