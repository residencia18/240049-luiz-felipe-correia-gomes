package br.com.lufecrx.crudexercise.api.controller.domain.wishlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lufecrx.crudexercise.api.model.Wishlist;
import br.com.lufecrx.crudexercise.api.services.domain.wishlist.WishlistService;

public class WishlistControllerTest {

    // TODO: Refactor the tests to use the new WishlistController

    private MockMvc mockMvc;

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wishlistController).build();
    }

    @Test
    public void testFindById() throws Exception {
        // Mock the service method to return a wishlist
        when(wishlistService.getWishlistById(anyLong())).thenReturn(Optional.of(new Wishlist()));

        // Perform the GET request to retrieve a wishlist by its ID
        mockMvc.perform(get("/wishlist/find-wishlist/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        // Create a new wishlist to be saved
        Wishlist wishlist = new Wishlist();
        wishlist.setName("Test Wishlist");

        // Mock the service method to return a new wishlist when a wishlist is created
        when(wishlistService.createWishlist(any(Wishlist.class))).thenReturn(new Wishlist());

        // Perform the POST request to create a new wishlist
        mockMvc.perform(post("/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(wishlist)))
                .andExpect(status().isOk());
    }

   @Test
    public void testAddProduct() {
        doNothing().when(wishlistService).addProductToWishlist(anyLong(), anyLong());

        ResponseEntity<String> response = wishlistController.addProduct(1L, 1L);

        assertEquals(bundle.getString("wishlist.successfully_added_product"), response.getBody());
        verify(wishlistService, times(1)).addProductToWishlist(1L, 1L);
    }

    @Test
    public void testUpdate() throws Exception {
        // Create a new wishlist to be updated
        Wishlist wishlist = new Wishlist();
        wishlist.setName("Test Wishlist");

        // Mock the service method to return a new wishlist when a wishlist is updated
        when(wishlistService.updateWishlist(anyLong(), any(Wishlist.class))).thenReturn(new Wishlist());

        mockMvc.perform(put("/wishlist/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(wishlist)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/wishlist/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}