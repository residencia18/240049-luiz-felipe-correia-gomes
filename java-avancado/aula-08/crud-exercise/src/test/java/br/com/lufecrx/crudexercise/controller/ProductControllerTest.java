package br.com.lufecrx.crudexercise.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.services.ProductService;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private List<Product> products;

    private Faker faker;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        products = fillProducts();
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

            assertEquals("Product created successfully.", response.getBody());
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

        assertEquals("Product updated successfully.", response.getBody());
        verify(productService, times(1)).updateProduct(1L, product);
    }

    @Test
    public void testDelete() {
        doNothing().when(productService).deleteProduct(anyLong());

        ResponseEntity<String> response = productController.delete(1L);

        assertEquals("Product deleted successfully.", response.getBody());
        verify(productService, times(1)).deleteProduct(1L);
    }
}