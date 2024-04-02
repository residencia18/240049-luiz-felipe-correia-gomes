package br.com.lufecrx.crudexercise.exceptions.user;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsersEmptyException extends RuntimeException {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public UsersEmptyException() {
        super(bundle.getString("user.empty_list"));
        log.error("Users list is empty.");
    }
}
