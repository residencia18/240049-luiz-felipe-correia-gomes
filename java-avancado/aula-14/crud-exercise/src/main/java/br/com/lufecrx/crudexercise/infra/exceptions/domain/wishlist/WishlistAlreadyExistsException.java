package br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WishlistAlreadyExistsException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public WishlistAlreadyExistsException(String name) {
        super(bundle.getString("wishlist.already_exists").replace("{name}", name));
        log.error("Wishlist with name {} already exists.", name);
    }
}
