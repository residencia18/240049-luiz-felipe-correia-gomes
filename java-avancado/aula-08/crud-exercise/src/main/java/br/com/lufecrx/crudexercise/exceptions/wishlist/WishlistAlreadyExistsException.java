package br.com.lufecrx.crudexercise.exceptions.wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WishlistAlreadyExistsException extends RuntimeException {

    public WishlistAlreadyExistsException(String name) {
        super("Wishlist with name " + name + " already exists.");
        log.error("Wishlist with name {} already exists.", name);
    }
}
