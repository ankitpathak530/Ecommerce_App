package com.jaspring.ProductService;

import com.jaspring.ProductService.entity.Product;
import com.jaspring.ProductService.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(ProductServiceApplication.class, args);
		ProductService productService = run.getBean(ProductService.class);


		saveProducts(productService);
	}

	private static void saveProducts(ProductService productService) {
		List<Product> products = List.of(new Product(101l, "Samsung Phone ", "Special Phone with 4Gb RAM", 10999,""),
				new Product(102l, "Lenovo Phone ", "Special Phone on 5 camera", 11999,""),
		new Product(103l, "Micromax Phone ", "Special Phone Almond display", 8999,""),
		new Product(104l, "Apple Phone ", "Special Phone earphone", 15999,""));
		productService.createProducts(products);
	}
}