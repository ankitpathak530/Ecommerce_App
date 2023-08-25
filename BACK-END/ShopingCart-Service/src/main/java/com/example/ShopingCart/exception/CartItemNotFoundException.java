package com.example.ShopingCart.exception;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CartItemNotFoundException extends RuntimeException{

    private String message;
}
