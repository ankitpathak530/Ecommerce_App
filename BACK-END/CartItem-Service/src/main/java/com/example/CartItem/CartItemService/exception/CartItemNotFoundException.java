package com.example.CartItem.CartItemService.exception;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartItemNotFoundException extends RuntimeException{

    private String message;
}
