package br.com.lufecrx.crudexercise.controller.domain.wishlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistAlreadyExistsException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistNotFoundException;
import br.com.lufecrx.crudexercise.model.Product;
import br.com.lufecrx.crudexercise.model.Wishlist;
import br.com.lufecrx.crudexercise.services.domain.wishlist.WishlistService;

public class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;

    @Mock
    private WishlistService wishlistService;

    private Faker faker;

    private ResourceBundle bundle;

    private List<Wishlist> wishlists;

    private List<Product> products;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        products = fillProducts();
        wishlists = fillWishlists();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
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

            assertEquals(bundle.getString("wishlist.successfully_created"), response.getBody());
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

        assertEquals(bundle.getString("wishlist.successfully_added_product"), response.getBody());
        verify(wishlistService, times(1)).addProductToWishlist(1L, 1L);
    }

    @Test
    public void testUpdate() {
        Wishlist wishlist = wishlists.get(1);

        when(wishlistService.updateWishlist(anyLong(), any(Wishlist.class))).thenReturn(wishlist);
        ResponseEntity<String> response = wishlistController.update(wishlist, 1L);

        assertEquals(bundle.getString("wishlist.successfully_updated"), response.getBody());
        verify(wishlistService, times(1)).updateWishlist(1L, wishlist);
    }

    @Test
    public void testDelete() {
        doNothing().when(wishlistService).deleteWishlist(anyLong());

        ResponseEntity<String> response = wishlistController.delete(1L);

        assertEquals(bundle.getString("wishlist.successfully_deleted"), response.getBody());
        verify(wishlistService, times(1)).deleteWishlist(1L);
    }

    @Test
    public void testWhenWishlistNotFound() {
        when(wishlistController.findById(anyLong())).thenThrow(new WishlistNotFoundException(1L));

        Exception exception = assertThrows(WishlistNotFoundException.class, () -> {
            wishlistController.findById(1L);
        });

        String expectedMessage = bundle.getString("wishlist.not_found").replace("{id}", "1");
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test 
    public void testWhenWishlistIsEmpty() {
        when(wishlistController.findAll()).thenThrow(new WishlistNotFoundException(1L));

        Exception exception = assertThrows(WishlistNotFoundException.class, () -> {
            wishlistController.findAll();
        });

        String expectedMessage = bundle.getString("wishlist.not_found").replace("{id}", "1");
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testWhenWishlistAlreadyExists() {
        Wishlist wishlist = wishlists.get(1);
        when(wishlistController.save(any(Wishlist.class))).thenThrow(new WishlistAlreadyExistsException(wishlist.getName()));

        Exception exception = assertThrows(WishlistAlreadyExistsException.class, () -> {
            wishlistController.save(wishlist);
        });

        String expectedMessage = bundle.getString("wishlist.already_exists").replace("{name}", wishlist.getName());
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}