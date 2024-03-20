package br.com.lufecrx.crudexercise.exceptions.wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WishlistNotFoundException extends RuntimeException {
    
    public WishlistNotFoundException(Long id) {
        super("Wishlist with id " + id + " not found.");
        log.error("Wishlist with id {} not found.", id);
    }
}
