package br.com.lufecrx.crudexercise.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductModelTests {

    @Test
    public void NewProductTest() {
        ProductModel product = new ProductModel(null, "Product 1", 10.0, null);
        assertEquals("Product 1", product.getProductName());
        assertEquals(10.0, product.getPrice());
    }

    @Test
    public void UpdateProductTest() {
        ProductModel product = new ProductModel(null, "Product 1", 10.0, null);
        product.setProductName("Product 2");
        assertEquals("Product 2", product.getProductName());
        assertEquals(10.0, product.getPrice());
    }

    @Test
    public void InvalidProductNameTest() {
        ProductModel product = new ProductModel(null, "P", 10.0, null);
        assertEquals(false, product.productNameIsValid());
    }

    @Test
    public void ValidProductNameTest() {
        ProductModel product = new ProductModel(null, "Product 1", 10.0, null);
        assertEquals(true, product.productNameIsValid());
    }
}
