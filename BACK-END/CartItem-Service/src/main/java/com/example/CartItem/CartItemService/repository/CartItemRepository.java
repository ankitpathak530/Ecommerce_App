package com.example.CartItem.CartItemService.repository;


import com.example.CartItem.CartItemService.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
      Long deleteByShoppingCartId(Long shoppingCartId);
      Optional<CartItem> findByProductIdAndShoppingCartId(Long productId, Long shoppingCartId);
}
