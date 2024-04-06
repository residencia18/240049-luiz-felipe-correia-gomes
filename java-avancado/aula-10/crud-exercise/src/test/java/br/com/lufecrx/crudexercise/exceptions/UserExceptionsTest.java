package br.com.lufecrx.crudexercise.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.exceptions.category.CategoriesEmptyException;
import br.com.lufecrx.crudexercise.exceptions.category.CategoryAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.user.UserNotFoundException;

public class UserExceptionsTest {
    
    private ResourceBundle bundle;

    private Faker faker;

    @BeforeEach
    public void init() {
        faker = new Faker();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Test
    public void testProductsEmptyException() {
        CategoriesEmptyException categoriesEmptyException = new CategoriesEmptyException();
        assertEquals(bundle.getString("category.empty_list"), categoriesEmptyException.getLocalizedMessage());
    }

    @Test
    public void testEmailAlreadyExistsException() {
        String fakerEmail = faker.internet().emailAddress();

        CategoryAlreadyExistsException categoryAlreadyExistsException = new CategoryAlreadyExistsException(fakerEmail);

        String expectedMessage = bundle.getString("category.already_exists").replace("{name}", fakerEmail);

        assertEquals(expectedMessage, categoryAlreadyExistsException.getLocalizedMessage());
    }

    @Test 
    public void testUserNotFoundException() {
        UserNotFoundException userNotFoundException = new UserNotFoundException(1L);
        String expectedMessage = bundle.getString("user.not_found").replace("{id}", "1");

        assertEquals(expectedMessage, userNotFoundException.getLocalizedMessage());
    }
}
