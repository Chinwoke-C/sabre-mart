package com.shoppingApp.orderservice.exception;

public class OrderException extends RuntimeException{
    public OrderException(){
        this("Order placement failed");
    }
    public OrderException(String message){
        super(message);
    }
}
