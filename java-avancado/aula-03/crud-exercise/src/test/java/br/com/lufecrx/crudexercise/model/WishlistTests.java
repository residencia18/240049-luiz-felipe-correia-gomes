package br.com.lufecrx.crudexercise.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WishlistTests {

    @Test
    public void NewWishlistTest() {
        Product product = new Product(null, "Product 1", 10.0, null, null);
        Wishlist wishlist = new Wishlist(null, "Wishlist 1", null);
        wishlist.addToWishlist(product);
        assertEquals("Wishlist 1", wishlist.getName());
        assertEquals(1, wishlist.getProducts().size());
        assertEquals(product, wishlist.getProducts().get(0));
    }

    @Test
    public void UpdateWishlistTest() {
        Product product = new Product(null, "Product 1", 10.0, null, null);
        Wishlist wishlist = new Wishlist(null, "Wishlist 1", null);
        wishlist.addToWishlist(product);
        wishlist.setName("Wishlist 2");
        assertEquals("Wishlist 2", wishlist.getName());
        assertEquals(1, wishlist.getProducts().size());
        assertEquals(product, wishlist.getProducts().get(0));
    }
}
