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

import br.com.lufecrx.crudexercise.repository.WishlistRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WishlistController.class)
public class WishlistControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistRepository repository;
    
    @Test
    public void postWishlistTest() throws Exception {
        mockMvc.perform(post("/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Wishlist 1\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void postNameInvalidTest() throws Exception {
        /* Wishlist name cannot be empty/blank/null  */
        mockMvc.perform(post("/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"\"}"))
                .andExpect(status().isBadRequest());
    }
}
