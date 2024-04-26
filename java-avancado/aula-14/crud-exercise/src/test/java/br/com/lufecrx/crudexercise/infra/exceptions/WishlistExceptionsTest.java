package br.com.lufecrx.crudexercise.infra.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistAlreadyExistsException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistNotFoundException;
import br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist.WishlistsEmptyException;

public class WishlistExceptionsTest {
    
    private ResourceBundle bundle;

    private Faker faker;

    @BeforeEach
    public void init() {
        faker = new Faker();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Test
    public void testWishlistEmptyException() {
        WishlistsEmptyException wishlistsEmptyException = new WishlistsEmptyException();
        assertEquals(bundle.getString("wishlist.empty_list"), wishlistsEmptyException.getLocalizedMessage());
    }

    @Test
    public void testWishlistAlreadyExistsException() {
        String fakerWishlist = faker.commerce().department();

        WishlistAlreadyExistsException wishlistAlreadyExistsException = new WishlistAlreadyExistsException(fakerWishlist);

        String expectedMessage = bundle.getString("wishlist.already_exists").replace("{name}", fakerWishlist);

        assertEquals(expectedMessage, wishlistAlreadyExistsException.getLocalizedMessage());
    }

    @Test 
    public void testWishlistNotFoundException() {
        WishlistNotFoundException wishlistNotFoundException = new WishlistNotFoundException(1L);

        String expectedMessage = bundle.getString("wishlist.not_found").replace("{id}", "1");

        assertEquals(expectedMessage, wishlistNotFoundException.getLocalizedMessage());
    }
}
