package br.com.lufecrx.crudexercise.api.infra.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.product.InvalidProductNameException;
import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.product.ProductNotFoundException;
import br.com.lufecrx.crudexercise.api.infra.exceptions.domain.product.ProductsEmptyException;

public class ProductExceptionsTest {
    
    private ResourceBundle bundle;

    private Faker faker;

    @BeforeEach
    public void init() {
        faker = new Faker();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Test
    public void testInvalidProductNameException() {
        String fakerProductName = faker.commerce().productName();

        InvalidProductNameException invalidProductNameException = new InvalidProductNameException(fakerProductName);

        String expectedMessage = bundle.getString("product.invalid_name").replace("{name}", fakerProductName);

        assertEquals(expectedMessage, invalidProductNameException.getLocalizedMessage());
    }

    @Test
    public void testProductsEmptyException () {
        ProductsEmptyException productsEmptyException = new ProductsEmptyException();
        assertEquals(bundle.getString("product.empty_list"), productsEmptyException.getLocalizedMessage());
    }
 
    @Test 
    public void testProductNotFoundException() {
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(1L);
        String expectedMessage = bundle.getString("product.not_found").replace("{id}", "1");

        assertEquals(expectedMessage, productNotFoundException.getLocalizedMessage());
    }
}