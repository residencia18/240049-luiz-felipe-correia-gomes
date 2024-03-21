package br.com.lufecrx.crudexercise.exceptions.product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found.");
        log.error("Product with id {} not found.", id);
    }
}
