package br.com.lufecrx.crudexercise.exceptions.wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WishlistsEmptyException extends RuntimeException {

    public WishlistsEmptyException() {
        super("Wishlist list is empty.");
        log.error("Wishlist list is empty.");
    }
}
