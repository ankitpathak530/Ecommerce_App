package com.example.ShopingCart.controller;

import com.example.ShopingCart.entity.ShoppingCart;
import com.example.ShopingCart.model.CartItem;
import com.example.ShopingCart.service.ShoppingCartService;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    private ShoppingCartService shoppingCartService;

    // Endpoint for checking the health of the service
    @GetMapping("/health")
    public String health() {
        return "Shopping-Cart service running healthy with instance-  " + instanceId;
    }





    // Endpoint to add a cart item to a shopping cart
    @PostMapping("/add-cartItem")
    public void addCartItemToShoppingCart(@RequestBody CartItem cartItem) {
        log.info("ShoppingCartController addCartItemToShoppingCart started...");

        // Call the service method to add the cart item to the shopping cart
        shoppingCartService.addCartItemToShoppingCart(cartItem);

        log.info("ShoppingCartController addCartItemToShoppingCart completed.");
    }




    // Endpoint to create a new shopping cart
    @PostMapping("/add-shoppingCart")
    public void createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        log.info("ShoppingCartController createShoppingCart started...");
        // Call the service method to create the shopping cart
        shoppingCartService.createShoppingCart(shoppingCart);
        log.info("ShoppingCartController createShoppingCart completed.");
    }




    @DeleteMapping("/delete-item/shoppingCartId/{shoppingCartId}")
    public void deleteShoppingCart(@PathVariable("shoppingCartId") Long shoppingCartId){
        log.info("ShoppingCartController deleteShoppingCart started...");
        // Call the service method to delete the shopping cart
        shoppingCartService.deleteShoppingCart(shoppingCartId);
        log.info("ShoppingCartController deleteShoppingCart completed.");
    }




    @DeleteMapping("/delete-item/{shoppingCartId}/{cartItemId}")
    public void deleteCartItemFromShoppingCart(@PathVariable("shoppingCartId") Long shoppingCartId, @PathVariable("cartItemId") Long cartItemId){
        log.info("ShoppingCartController deleteCartItemFromShoppingCart started...");
        // Call the service method to delete the cart item from the shopping cart
        shoppingCartService.deleteCartItemFromShoppingCart(shoppingCartId, cartItemId);
        log.info("ShoppingCartController deleteCartItemFromShoppingCart completed.");
    }
}