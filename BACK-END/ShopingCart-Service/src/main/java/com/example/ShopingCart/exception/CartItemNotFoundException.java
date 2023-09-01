package com.example.ShopingCart.exception;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemNotFoundException extends RuntimeException{

    private String message;
}
