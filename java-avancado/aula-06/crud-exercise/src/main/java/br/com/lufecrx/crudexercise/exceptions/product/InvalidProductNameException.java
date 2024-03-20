package br.com.lufecrx.crudexercise.exceptions.product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidProductNameException extends RuntimeException {

    public InvalidProductNameException(String name) {
        super("Invalid product name: " + name);
        log.error("Invalid product name: {}", name);  
    }
}