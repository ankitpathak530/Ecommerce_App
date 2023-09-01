package com.example.ShopingCart.exception;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvalidShoppingCartException extends RuntimeException{

    private String message;
}
