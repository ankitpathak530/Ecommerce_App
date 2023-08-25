package com.jaspring.ProductService.controller;


import com.jaspring.ProductService.entity.Product;
import com.jaspring.ProductService.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/product")
@RestController
@Slf4j
public class ProductController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    private ProductService productService;

    @GetMapping("/health")
    public String health(){
         return "Product service running healthy with instance-  "+ instanceId;
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){
         Product product = this.productService.getProductById(productId);
         if(product == null)
             return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
         return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
    }



    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products = this.productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatusCode.valueOf(200));
    }
}