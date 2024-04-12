package br.com.lufecrx.crudexercise.infra.exceptions.domain.product;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductsEmptyException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public ProductsEmptyException() {
        super(bundle.getString("product.empty_list"));
        log.error("Products list is empty.");
    }
}
