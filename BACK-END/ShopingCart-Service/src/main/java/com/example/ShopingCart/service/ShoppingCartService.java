package com.example.ShopingCart.service;

import com.example.ShopingCart.entity.ShoppingCart;
import com.example.ShopingCart.exception.CartItemNotFoundException;
import com.example.ShopingCart.exception.InvalidShoppingCartException;
import com.example.ShopingCart.exception.ShoppingCartNotFoundException;
import com.example.ShopingCart.feign.CartItemClient;
import com.example.ShopingCart.model.CartItem;
import com.example.ShopingCart.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private CartItemClient cartItemClient;



    @Autowired
    ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CartItemClient cartItemClient){
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemClient = cartItemClient;
    }



    public void addCartItemToShoppingCart(CartItem cartItem) {
        log.info("ShoppingCartService addCartItemToShoppingCart started...");

        // Find the shopping cart by its ID or throw an exception if not found
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartItem.getShoppingCartId())
                .orElseThrow(() -> new ShoppingCartNotFoundException("ShoppingCart not found with id: " + cartItem.getShoppingCartId()));

        //save cart-item to cartItem microservice
        ResponseEntity<CartItem> newCartRes = cartItemClient.addCartItem(cartItem);
        if(newCartRes.getStatusCode().is2xxSuccessful()){
            // Add the cart item to the shopping cart and save the updated cart
            //if this cartItemIds is already added there and client has requested only few update in cart item then don't require to save again with same cart item ids
            //if this cartItemIds is new then add this carItem ids to shopping cart and save.
            if(!shoppingCart.getCartItemIds().contains(newCartRes.getBody().getCartItemId())){
                shoppingCart.addCartItem(newCartRes.getBody().getCartItemId());
                shoppingCartRepository.save(shoppingCart);
            }

        }else{
            throw new RuntimeException("CartItem not saved.");
        }
        log.info("ShoppingCartService addCartItemToShoppingCart completed.");
    }




    public void createShoppingCart(ShoppingCart shoppingCart) {
        log.info("ShoppingCartService createShoppingCart started...");
        // Save the provided shopping cart and handle the case where saving fails
        if(shoppingCart.getShoppingCartName() == null || shoppingCart.getShoppingCartName().trim().equals("")){
            throw new InvalidShoppingCartException("Shopping Cart name is invalid.");
        }

        Optional<ShoppingCart> byShoppingCartName = this.shoppingCartRepository.findByShoppingCartName(shoppingCart.getShoppingCartName());
        if(!byShoppingCartName.isEmpty()){
            throw new InvalidShoppingCartException("Shopping Cart already created with this name. Try different name");
        }

        this.shoppingCartRepository.save(shoppingCart);
        log.info("ShoppingCartService createShoppingCart completed.");
    }




    @Transactional
    public void deleteShoppingCart(Long shoppingCartId) {
        log.info("ShoppingCartService deleteShoppingCart started....");
        //check shopping cart is available in db or not
        ShoppingCart newshoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow(()-> new ShoppingCartNotFoundException("Shopping Cart not found with id: "+shoppingCartId));
       //empty all cartItem from shopping cart and save.
        newshoppingCart.setCartItemIds(new ArrayList<>());
        this.shoppingCartRepository.save(newshoppingCart);

        //DeAssociate all cartItem with this shoppingCart in CartItem-microservices.
        cartItemClient.deleteCartItemByShoppingCartId(shoppingCartId);
        //now delete
        this.shoppingCartRepository.deleteById(shoppingCartId);
        log.info("ShoppingCartService deleteShoppingCart completed.");
    }



    @Transactional
    public void deleteCartItemFromShoppingCart(Long shoppingCartId, Long cartItemId) {

        log.info("ShoppingCartService deleteCartItemFromShoppingCart Started.....");
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(shoppingCartId).orElseThrow(()-> new RuntimeException("Shopping Cart not found with id: "+shoppingCartId));
        List<Long> cartItemIds = shoppingCart.getCartItemIds();

        if(!cartItemIds.contains(cartItemId)){
            throw new CartItemNotFoundException("CartItem not found with id: "+cartItemId);
        }
        cartItemIds.remove(cartItemId);
        shoppingCart.setCartItemIds(cartItemIds);

        this.shoppingCartRepository.save(shoppingCart);

        //remove cart item also from cartItem microservice
        this.cartItemClient.deleteCartItemByCartItemId(cartItemId);

        log.info("ShoppingCartService deleteCartItemFromShoppingCart completed.");
    }
}