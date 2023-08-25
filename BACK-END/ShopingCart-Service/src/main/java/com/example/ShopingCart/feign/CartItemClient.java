package com.example.ShopingCart.feign;


import com.example.ShopingCart.model.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${microservices.apiGateway.uri}/cartItem/", name = "CartItem-Service")
public interface CartItemClient {

    @PostMapping("/")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem);


        @DeleteMapping("/delete-item/cartItemId/{cartItemId}")
    public ResponseEntity<?> deleteCartItemByCartItemId(@PathVariable("cartItemId") Long cartItemId);


    @DeleteMapping("/delete-item/shoppingCartId/{shoppingCartId}")
    public ResponseEntity<?> deleteCartItemByShoppingCartId(@PathVariable("shoppingCartId") Long shoppingCartId);

}