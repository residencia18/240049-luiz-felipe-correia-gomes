package br.com.lufecrx.crudexercise.exceptions.category;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryNotFoundException extends RuntimeException {
    
    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public CategoryNotFoundException(Long id) {
        super(bundle.getString("category.not_found").replace("{id}", id.toString()));
        log.error("Category with id {} not found.", id);
    }
}
