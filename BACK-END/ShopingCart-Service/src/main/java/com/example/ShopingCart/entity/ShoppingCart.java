package com.example.ShopingCart.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartId;
    private String shoppingCartName;
    private Long userId;

    @ElementCollection
    private List<Long> cartItemIds = new ArrayList<>();

    public ShoppingCart(Long userId){
        this.userId = userId;
    }

    public void addCartItem(Long cartItemId) {
        cartItemIds.add(cartItemId);
    }
}
