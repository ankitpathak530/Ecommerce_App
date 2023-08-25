package com.example.ShopingCart.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class CartItem {

    private Long cartItemId;

    private Long shoppingCartId;
    private Long productId;
    private int quantity;
}