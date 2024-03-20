package br.com.lufecrx.crudexercise.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductModelTests {

    @Test
    public void NewProductTest() {
        Product product = new Product(null, "Product 1", 10.0, null, null);
        assertEquals("Product 1", product.getProductName());
        assertEquals(10.0, product.getPrice());
    }

    @Test
    public void UpdateProductTest() {
        Product product = new Product(null, "Product 1", 10.0, null, null);
        product.setProductName("Product 2");
        assertEquals("Product 2", product.getProductName());
        assertEquals(10.0, product.getPrice());
    }
}
