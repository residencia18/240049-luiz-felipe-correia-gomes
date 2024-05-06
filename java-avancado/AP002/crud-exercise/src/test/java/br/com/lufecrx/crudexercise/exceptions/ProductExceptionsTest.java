package br.com.lufecrx.crudexercise.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.exceptions.api.domain.product.InvalidProductNameException;
import br.com.lufecrx.crudexercise.exceptions.api.domain.product.ProductNotFoundException;
import br.com.lufecrx.crudexercise.exceptions.api.domain.product.ProductsEmptyException;
import br.com.lufecrx.crudexercise.exceptions.api.handler.ProductExceptionsHandler;
import br.com.lufecrx.crudexercise.exceptions.global.message.RestErrorMessage;

public class ProductExceptionsTest {

    private ProductExceptionsHandler productExceptionsHandler;
    
    private ResourceBundle bundle;

    private Faker faker;

    @BeforeEach
    public void init() {
        productExceptionsHandler = new ProductExceptionsHandler();
        faker = new Faker();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Test
    public void testInvalidProductNameException() {
        String fakerProductName = faker.commerce().productName();
        InvalidProductNameException invalidProductNameException = new InvalidProductNameException(fakerProductName);
        String expectedMessage = bundle.getString("product.invalid_name").replace("{name}", fakerProductName);

        ResponseEntity<RestErrorMessage> responseEntity = productExceptionsHandler.handleInvalidProductNameException(invalidProductNameException);

        // Assert that the response status is BAD_REQUEST and the message is the expected one
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());        
        assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }

    @Test
    public void testProductsEmptyException () {
        ProductsEmptyException productsEmptyException = new ProductsEmptyException();
        assertEquals(bundle.getString("product.empty_list"), productsEmptyException.getLocalizedMessage());
        String expectedMessage = bundle.getString("product.empty_list");

        ResponseEntity<RestErrorMessage> responseEntity = productExceptionsHandler.handleProductsEmptyException(productsEmptyException);

        // Assert that the response status is NOT_FOUND and the message is the expected one
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }
 
    @Test 
    public void testProductNotFoundException() {
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(1L);
        String expectedMessage = bundle.getString("product.not_found").replace("{id}", "1");

        ResponseEntity<RestErrorMessage> responseEntity = productExceptionsHandler.handleProductNotFoundException(productNotFoundException);

        // Assert that the response status is NOT_FOUND and the message is the expected one
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(expectedMessage, responseEntity.getBody().getMessage());
    }

}