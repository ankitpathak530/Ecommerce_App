package com.example.CartItem.CartItemService.controller;

import com.example.CartItem.CartItemService.entity.CartItem;
import com.example.CartItem.CartItemService.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItem")
@Slf4j
public class CartItemController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    private CartItemService cartItemService;

    // Endpoint for checking the health of the service
    @GetMapping("/health")
    public String health() {
        return "CartItem service running healthy with instance-  " + instanceId;
    }


    @PostMapping("/")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        log.info("CartItemController addCartItem started...");
        // Create the cart item using the service
        CartItem cartItem1 = this.cartItemService.createCartItem(cartItem);
        // Check if cart item creation was successful
        if (cartItem1 == null) {
            log.info("CartItemController addCartItem: Cart item creation failed.");
            return ResponseEntity.badRequest().build();
        }
        log.info("CartItemController addCartItem completed.");
        return new ResponseEntity<>(cartItem1, HttpStatus.OK);
    }




    @DeleteMapping("/delete-item/cartItemId/{cartItemId}")
    public ResponseEntity<?> deleteCartItemByCartItemId(@PathVariable("cartItemId") Long cartItemId) {
        log.info("CartItemController deleteCartItemByCartItemId started...");
        // Delete the cart item by its ID
        this.cartItemService.deleteCartItemByCartItemId(cartItemId);
        log.info("CartItemController deleteCartItemByCartItemId completed.");
        return new ResponseEntity(HttpStatus.OK);
    }




    @DeleteMapping("/delete-item/shoppingCartId/{shoppingCartId}")
    public ResponseEntity<?> deleteCartItemByShoppingCartId(@PathVariable("shoppingCartId") Long shoppingCartId) {
        log.info("CartItemController deleteCartItemOfShoppingCartId started...");
        // Delete all cart items of a shopping cart
        this.cartItemService.deleteCartItemByShoppingCartId(shoppingCartId);
        log.info("CartItemController deleteCartItemOfShoppingCartId completed.");
        return new ResponseEntity(HttpStatus.OK);
    }
}
