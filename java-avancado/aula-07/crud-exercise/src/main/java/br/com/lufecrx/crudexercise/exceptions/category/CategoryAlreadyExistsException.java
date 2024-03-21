package br.com.lufecrx.crudexercise.exceptions.category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String name) {
        super("Category with name " + name + " already exists.");
        log.error("Category with name {} already exists.", name);
    }    
}
