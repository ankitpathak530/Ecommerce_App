package com.example.CartItem.CartItemService.exception;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartNotFoundException extends RuntimeException{

    private String message;
}
