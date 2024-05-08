package br.com.lufecrx.crudexercise.api.controller.domain.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lufecrx.crudexercise.api.model.Product;
import br.com.lufecrx.crudexercise.api.services.domain.product.ProductService;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testSaveProduct() throws Exception {
        // Create a new product to be saved
        Product product = new Product();
        product.setProductName("Test Product");
        product.setPrice(10.0);
    
        // Mock the service method to return the product object when a product is created
        when(productService.createProduct(any(Product.class))).thenReturn(product);
    
        // Perform the POST request to create a new product
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk()); // Expect a 200 status code
    }

    @Test
    public void testFindProductById() throws Exception {
        // Mock the service method to return a new product object when a product is found by ID
        when(productService.getProductById(anyLong())).thenReturn(Optional.of(new Product()));

        // Perform the GET request to find a product by ID
        mockMvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        // Create a new product to be updated
        Product product = new Product();
        product.setProductName("Test Product");
        product.setPrice(10.0);
        
        // Mock the service method to return a new product object when a product is updated
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(new Product());

        // Perform the PUT request to update the product
        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Perform the DELETE request to delete a product by ID
        mockMvc.perform(delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}