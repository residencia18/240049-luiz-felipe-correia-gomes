package br.com.lufecrx.crudexercise.infra.exceptions.domain.wishlist;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WishlistsEmptyException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public WishlistsEmptyException() {
        super(bundle.getString("wishlist.empty_list"));
        log.error("Wishlist list is empty.");
    }
}
