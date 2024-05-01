package br.com.lufecrx.crudexercise.api.infra.exceptions.domain.category;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryAlreadyExistsException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public CategoryAlreadyExistsException(String name) {
        super(bundle.getString("category.already_exists").replace("{name}", name));
        log.error("Category with name {} already exists.", name);
    }
}
