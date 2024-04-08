package br.com.lufecrx.crudexercise.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.exceptions.product.InvalidProductNameException;
import br.com.lufecrx.crudexercise.exceptions.product.ProductNotFoundException;
import br.com.lufecrx.crudexercise.exceptions.product.ProductsEmptyException;
import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.services.ProductService;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private List<Product> products;

    private Faker faker;

    private ResourceBundle bundle;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        products = fillProducts();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    public List<Product> fillProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            String productName = faker.commerce().productName();
            Double price = faker.number().randomDouble(2, 0, 1000);

            product.setProductName(productName);
            product.setPrice(price);
            products.add(product);
        }

        return products;
    }

    @Test
    public void testSave() {
        for (Product product : products) {
            when(productService.createProduct(any(Product.class))).thenReturn(product);

            ResponseEntity<String> response = productController.save(product);

            assertEquals(bundle.getString("product.successfully_created"), response.getBody());
            verify(productService, times(1)).createProduct(product);
        }
    }

    @Test
    public void testFindAll() {

        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<Iterable<Product>> response = productController.findAll();

        assertEquals(products.size(), ((Collection<?>) response.getBody()).size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testFindById() {

        Product product = products.get(4);

        when(productService.getProductById(anyLong())).thenReturn(Optional.of(product));

        ResponseEntity<Optional<Product>> response = productController.findById(4L);

        assertEquals(product, response.getBody().get());
        verify(productService, times(1)).getProductById(4L);
    }

    @Test
    public void testUpdate() {
        Product product = products.get(1);

        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(product);

        ResponseEntity<String> response = productController.update(1L, product);

        assertEquals(bundle.getString("product.successfully_updated"), response.getBody());
        verify(productService, times(1)).updateProduct(1L, product);
    }

    @Test
    public void testDelete() {
        doNothing().when(productService).deleteProduct(anyLong());

        ResponseEntity<String> response = productController.delete(1L);

        assertEquals(bundle.getString("product.successfully_deleted"), response.getBody());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    public void testWhenDeleteProductNotFound() {
        Long productId = 1L;

        doThrow(new ProductNotFoundException(productId)).when(productService).deleteProduct(productId);

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productController.delete(productId);
        });

        String expectedMessage = bundle.getString("product.not_found").replace("{id}", productId.toString());
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testWhenInvalidProductName() {
        Product product = products.get(5);

        product.setProductName(""); // Product name cannot be blank

        Exception exception = assertThrows(InvalidProductNameException.class, () -> {
            ProductService.validateProductName(product.getProductName());
        });

        String expectedMessage = bundle.getString("product.invalid_name").replace("{name}", product.getProductName());
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testWhenProductsEmpty() {
        when(productController.findAll()).thenThrow(new ProductsEmptyException());

        Exception exception = assertThrows(ProductsEmptyException.class, () -> {
            productController.findAll();
        });

        String expectedMessage = bundle.getString("product.empty_list");
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}