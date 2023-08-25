package com.example.CartItem.CartItemService.service;

import com.example.CartItem.CartItemService.entity.CartItem;
import com.example.CartItem.CartItemService.exception.CartItemNotFoundException;
import com.example.CartItem.CartItemService.exception.ShoppingCartNotFoundException;
import com.example.CartItem.CartItemService.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;




    @Transactional
    public CartItem createCartItem(CartItem cartItem) {
        log.info("CartItemService createCartItem started...");
        // Save the cart item
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        log.info("CartItemService createCartItem completed.");
        return savedCartItem;
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