package br.com.lufecrx.crudexercise.exceptions.auth.domain.user;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAlreadyVerified extends RuntimeException{
    
    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    public UserAlreadyVerified() {
        super(bundle.getString("user.already_verified"));
        log.error(bundle.getString("user.already_verified"));
    }
    
    public UserAlreadyVerified(Throwable cause) {
        super(bundle.getString("user.already_verified"), cause);
        log.error(bundle.getString("user.already_verified"), cause);
    }
}
