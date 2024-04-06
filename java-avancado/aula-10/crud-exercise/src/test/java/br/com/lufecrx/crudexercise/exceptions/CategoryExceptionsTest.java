package br.com.lufecrx.crudexercise.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.exceptions.category.CategoriesEmptyException;
import br.com.lufecrx.crudexercise.exceptions.category.CategoryAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.category.CategoryNotFoundException;

public class CategoryExceptionsTest {
    
    private ResourceBundle bundle;

    private Faker faker;

    @BeforeEach
    public void init() {
        faker = new Faker();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Test
    public void testCategoriesEmptyException() {
        CategoriesEmptyException categoriesEmptyException = new CategoriesEmptyException();
        assertEquals(bundle.getString("category.empty_list"), categoriesEmptyException.getLocalizedMessage());
    }

    @Test
    public void testCategoryAlreadyExistsException() {
        String fakerCategory = faker.commerce().department();
        CategoryAlreadyExistsException categoryAlreadyExistsException = new CategoryAlreadyExistsException(fakerCategory);

        String expectedMessage = bundle.getString("category.already_exists").replace("{name}", fakerCategory);

        assertEquals(expectedMessage, categoryAlreadyExistsException.getLocalizedMessage());
    }

    @Test 
    public void testCategoryNotFoundException() {
        CategoryNotFoundException categoryNotFoundException = new CategoryNotFoundException(1L);
        String expectedMessage = bundle.getString("category.not_found").replace("{id}", "1");

        assertEquals(expectedMessage, categoryNotFoundException.getLocalizedMessage());
    }
}
