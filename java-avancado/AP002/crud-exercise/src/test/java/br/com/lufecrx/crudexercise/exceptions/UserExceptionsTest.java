package br.com.lufecrx.crudexercise.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.exceptions.api.domain.product.ProductsEmptyException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotFoundException;

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
        ProductsEmptyException productsEmptyException = new ProductsEmptyException();
        String expectedMessage = bundle.getString("product.empty_list");

        assertEquals(expectedMessage, productsEmptyException.getLocalizedMessage());
    }

    @Test
    public void testEmailAlreadyExistsException() {
        String fakerEmail = faker.internet().emailAddress();

        EmailAlreadyExistsException emailAlreadyExistsException = new EmailAlreadyExistsException(fakerEmail);
        String expectedMessage = bundle.getString("user.already_exists").replace("{email}", fakerEmail);

        assertEquals(expectedMessage, emailAlreadyExistsException.getLocalizedMessage());
    }

    @Test 
    public void testUserNotFoundException() {
        UserNotFoundException userNotFoundException = new UserNotFoundException(1L);
        String expectedMessage = bundle.getString("user.not_found").replace("{id}", "1");

        assertEquals(expectedMessage, userNotFoundException.getLocalizedMessage());
    }
}
