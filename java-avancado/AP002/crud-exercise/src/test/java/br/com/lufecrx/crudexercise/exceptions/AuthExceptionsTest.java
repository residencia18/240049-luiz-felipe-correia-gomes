package br.com.lufecrx.crudexercise.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidCredentialsException;
import br.com.lufecrx.crudexercise.exceptions.auth.handler.AuthExceptionsHandler;
import br.com.lufecrx.crudexercise.exceptions.global.message.RestErrorMessage;

public class AuthExceptionsTest {

    private AuthExceptionsHandler authExceptionsHandler;

    private InvalidCredentialsException invalidCredentialsException;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @BeforeEach
    public void init() {
        authExceptionsHandler = new AuthExceptionsHandler();
        invalidCredentialsException = mock(InvalidCredentialsException.class);
    }

    @Test
    public void testHandleInvalidCredentialsException() {
        String exceptionMessage = bundle.getString("auth.invalid_credentials");
        when(invalidCredentialsException.getMessage()).thenReturn(exceptionMessage);

        ResponseEntity<RestErrorMessage> responseEntity = authExceptionsHandler.handleInvalidCredentialsException(invalidCredentialsException);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals(exceptionMessage, responseEntity.getBody().getMessage());
    }
}