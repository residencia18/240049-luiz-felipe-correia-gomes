package br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WishlistNotFoundException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public WishlistNotFoundException(Long id) {
        super(bundle.getString("wishlist.not_found").replace("{id}", id.toString()));
        log.error("Wishlist with id {} not found.", id);
    }
}
