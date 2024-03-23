package br.com.lufecrx.crudexercise.exceptions.category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoriesEmptyException extends RuntimeException {

    public CategoriesEmptyException() {
        super("Categories list is empty.");
        log.error("Categories list is empty.");
    }
}
