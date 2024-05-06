package br.com.lufecrx.crudexercise.exceptions.api.domain.category;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoriesEmptyException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public CategoriesEmptyException() {
        super(bundle.getString("category.empty_list"));
        log.error("Categories list is empty.");
    }
}
