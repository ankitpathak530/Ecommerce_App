package com.example.ShopingCart;

import com.example.ShopingCart.entity.ShoppingCart;
import com.example.ShopingCart.repository.ShoppingCartRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ShoppingCartServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ShoppingCartServiceApplication.class, args);
	}
}
