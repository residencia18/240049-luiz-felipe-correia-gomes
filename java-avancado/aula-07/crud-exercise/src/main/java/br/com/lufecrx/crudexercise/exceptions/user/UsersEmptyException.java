package br.com.lufecrx.crudexercise.exceptions.user;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsersEmptyException extends RuntimeException{
    
    public UsersEmptyException() {
        super("Users list is empty.");
        log.error("Users list is empty.");        
    }
}
