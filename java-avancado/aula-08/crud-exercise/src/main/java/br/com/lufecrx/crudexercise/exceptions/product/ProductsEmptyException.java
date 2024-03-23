package br.com.lufecrx.crudexercise.exceptions.product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductsEmptyException extends RuntimeException {
    
    public ProductsEmptyException() {
        super("Products list is empty.");
        log.error("Products list is empty.");
    }
}
