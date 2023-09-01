package com.example.ShopingCart.exception;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingCartNotFoundException extends RuntimeException{

    private String message;
}
