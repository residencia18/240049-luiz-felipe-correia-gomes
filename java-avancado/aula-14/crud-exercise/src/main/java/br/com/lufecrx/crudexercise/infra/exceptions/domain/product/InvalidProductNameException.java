package br.com.lufecrx.crudexercise.infra.exceptions.domain.product;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidProductNameException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public InvalidProductNameException(String name) {
        super(bundle.getString("product.invalid_name").replace("{name}", name));
        log.error("Invalid product name: {}", name);
    }
}