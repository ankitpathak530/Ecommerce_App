package com.example.ShopingCart.repository;

import com.example.ShopingCart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

       public ShoppingCart findByUserId(Long userId);


}
