package com.example.CartItem.CartItemService.service;

import com.example.CartItem.CartItemService.entity.CartItem;
import com.example.CartItem.CartItemService.exception.CartItemNotFoundException;
import com.example.CartItem.CartItemService.exception.ShoppingCartNotFoundException;
import com.example.CartItem.CartItemService.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;




    @Transactional
    public CartItem createCartItem(CartItem cartItem) {
        log.info("CartItemService createCartItem started...");
        Optional<CartItem> byProductId = cartItemRepository.findByProductIdAndShoppingCartId(cartItem.getProductId(), cartItem.getShoppingCartId());
        if(byProductId.isEmpty()){
            //save new cart item
            CartItem newSavedCartItem = cartItemRepository.save(cartItem);
            log.info("CartItemService createCartItem completed.");
            return newSavedCartItem;
        }
        else{
            //update the cart item quantity do not create new one
            CartItem cartItem1 = byProductId.get();
            cartItem1.setQuantity(cartItem.getQuantity());
            log.info("CartItemService createCartItem completed.");
            return this.cartItemRepository.save(cartItem1);
        }
    }





    @Transactional
    public void deleteCartItemByCartItemId(Long cartItemId) {
        log.info("CartItemService deleteCartItemByCartItemId started...");

        // Check if the cart item exists
        CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found with id: " + cartItemId));

        // Delete the cart item
        this.cartItemRepository.delete(cartItem);
        log.info("CartItemService deleteCartItemByCartItemId completed.");
    }



    @Transactional
    public void deleteCartItemByShoppingCartId(Long shoppingCartId) {
        log.info("CartItemService deleteCartItemFromShoppingCart started...");

        // Delete cart item by shopping cart id
        Long count = this.cartItemRepository.deleteByShoppingCartId(shoppingCartId);
        if(count <= 0){
            throw new ShoppingCartNotFoundException("ShoppingCart not found with id: "+shoppingCartId);
        }
        log.info("CartItemService deleteCartItemFromShoppingCart completed.");
    }
}