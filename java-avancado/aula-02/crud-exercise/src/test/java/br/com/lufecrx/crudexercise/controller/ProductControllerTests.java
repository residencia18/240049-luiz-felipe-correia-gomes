package br.com.lufecrx.crudexercise.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.lufecrx.crudexercise.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository repository;

    @Test
    public void postProductTest() throws Exception {
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"productName\": \"Product 1\", \"price\": 10.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void postProductNameInvalidTest() throws Exception {
        /* Product name must have at least 3 characters */

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"productName\": \"P\", \"price\": 10.0}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postPriceInvalidTest() throws Exception {
        /* Product price must be greater than 0 */

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"productName\": \"Product 1\", \"price\": -10.0}"))
                .andExpect(status().isBadRequest());
    }
}
