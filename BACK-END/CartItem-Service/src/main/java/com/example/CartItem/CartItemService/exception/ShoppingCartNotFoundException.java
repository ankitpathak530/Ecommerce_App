package com.example.CartItem.CartItemService.exception;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingCartNotFoundException extends RuntimeException{

    private String message;
}
