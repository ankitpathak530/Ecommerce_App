package com.jaspring.ProductService.service;


import com.jaspring.ProductService.entity.Product;
import com.jaspring.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

      @Autowired
      private ProductRepository productRepository;

      public Product getProductById(Long productId){
          Optional<Product> byId = this.productRepository.findById(productId);
          return byId.get();
      }

      public Product createProduct(Product product){
          return this.productRepository.save(product);
      }
     public List<Product> createProducts(List<Product> products){
         return this.productRepository.saveAll(products);
    }

    public List<Product> getAllProduct() {
          return this.productRepository.findAll();
    }
}
