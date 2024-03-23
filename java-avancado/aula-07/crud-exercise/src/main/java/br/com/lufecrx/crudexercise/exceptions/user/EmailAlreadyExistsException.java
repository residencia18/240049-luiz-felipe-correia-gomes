package br.com.lufecrx.crudexercise.exceptions.user;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("User with email " + email + " already exists");
        log.error("User with email {} already exists", email);
    }
}
