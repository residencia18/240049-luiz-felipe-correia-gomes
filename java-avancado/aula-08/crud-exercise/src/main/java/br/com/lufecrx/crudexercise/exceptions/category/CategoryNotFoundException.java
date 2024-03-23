package br.com.lufecrx.crudexercise.exceptions.category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryNotFoundException extends RuntimeException {
    
    public CategoryNotFoundException(Long id) {
        super("Category with id " + id + " not found.");
        log.error("Category with id {} not found.", id);
    }
}
