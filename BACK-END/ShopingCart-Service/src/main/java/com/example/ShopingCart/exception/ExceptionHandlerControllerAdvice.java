package com.example.ShopingCart.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(value = {ShoppingCartNotFoundException.class})
    public ResponseEntity<Object> handleValidationException(ShoppingCartNotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "ShoppingCart Not Found");
        body.put("Description:", ex.getMessage());

        return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(body);
    }


    @ExceptionHandler(value = {CartItemNotFoundException.class})
    public ResponseEntity<Object> handleValidationException(CartItemNotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "CartItem Not Found");
        body.put("Description:", ex.getMessage());

        return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(body);
    }



    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAllOtherExceptions(Exception ex){

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Exception thrown");
        body.put("Description:", ex.getMessage());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}