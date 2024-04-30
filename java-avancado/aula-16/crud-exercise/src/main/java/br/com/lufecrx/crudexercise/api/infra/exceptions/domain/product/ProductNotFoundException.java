package br.com.lufecrx.crudexercise.api.infra.exceptions.domain.product;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductNotFoundException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public ProductNotFoundException(Long id) {
        super(bundle.getString("product.not_found").replace("{id}", id.toString()));
        log.error("Product with id {} not found.", id);
    }
}
