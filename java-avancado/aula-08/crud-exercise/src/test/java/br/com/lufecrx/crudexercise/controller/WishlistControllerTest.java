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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.services.WishlistService;

public class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;

    @Mock
    private WishlistService wishlistService;

    private Faker faker;

    private List<Wishlist> wishlists;

    private List<Product> products;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        products = fillProducts();
        wishlists = fillWishlists();
    }

    public List<Product> fillProducts() {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            String name = faker.commerce().productName();
            product.setProductName(name);
            product.setPrice(faker.number().randomDouble(2, 1, 1000));
            products.add(product);
        }

        return products;
    }

    public List<Wishlist> fillWishlists() {
        List<Wishlist> wishlists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Wishlist wishlist = new Wishlist();
            String name = faker.commerce().productName();
            wishlist.setName(name);

            for (int j = 0; j < 5; j++) {
                Product product = products.get(j);
                wishlist.addToWishlist(product);
            }
            
            wishlists.add(wishlist);
        }

        return wishlists;
    }

    @Test
    public void testSave() {
        for (Wishlist wishlist : wishlists) {
            when(wishlistService.createWishlist(any(Wishlist.class))).thenReturn(wishlist);

            ResponseEntity<String> response = wishlistController.save(wishlist);

            assertEquals("Wishlist created successfully.", response.getBody());
            verify(wishlistService, times(1)).createWishlist(wishlist);
        }
    }

    @Test
    public void testFindAll() {
        when(wishlistService.getAllWishlists()).thenReturn(wishlists);

        ResponseEntity<Iterable<Wishlist>> response = wishlistController.findAll();

        assertEquals(wishlists.size(), ((Collection<?>) response.getBody()).size());
        verify(wishlistService, times(1)).getAllWishlists();
    }

    @Test
    public void testAddProduct() {
        doNothing().when(wishlistService).addProductToWishlist(anyLong(), anyLong());

        ResponseEntity<String> response = wishlistController.addProduct(1L, 1L);

        assertEquals("Product added to wishlist successfully.", response.getBody());
        verify(wishlistService, times(1)).addProductToWishlist(1L, 1L);
    }

    @Test
    public void testUpdate() {
        Wishlist wishlist = wishlists.get(1);

        when(wishlistService.updateWishlist(anyLong(), any(Wishlist.class))).thenReturn(wishlist);
        ResponseEntity<String> response = wishlistController.update(wishlist, 1L);

        assertEquals("Wishlist updated successfully.", response.getBody());
        verify(wishlistService, times(1)).updateWishlist(1L, wishlist);
    }

    @Test
    public void testDelete() {
        doNothing().when(wishlistService).deleteWishlist(anyLong());

        ResponseEntity<String> response = wishlistController.delete(1L);

        assertEquals("Wishlist deleted successfully.", response.getBody());
        verify(wishlistService, times(1)).deleteWishlist(1L);
    }
}