package br.com.lufecrx.crudexercise.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidCredentialsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidOtpException;
import br.com.lufecrx.crudexercise.exceptions.auth.handler.AuthExceptionsHandler;
import br.com.lufecrx.crudexercise.exceptions.global.message.RestErrorMessage;

public class AuthExceptionsTest {

    private AuthExceptionsHandler authExceptionsHandler;

    private ResourceBundle bundle;

    @BeforeEach
    public void init() {
        authExceptionsHandler = new AuthExceptionsHandler();
        bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    @Test
    public void testHandleInvalidCredentialsException() {
        InvalidCredentialsException invalidCredentialsException = new InvalidCredentialsException();
        String exceptionMessage = bundle.getString("auth.invalid_credentials");

        ResponseEntity<RestErrorMessage> responseEntity = authExceptionsHandler
                .handleInvalidCredentialsException(invalidCredentialsException);

        // Assert that the response status is UNAUTHORIZED and the message is the expected one
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals(exceptionMessage, responseEntity.getBody().getMessage());
    }

    @Test
    public void testInvalidOtpException() {
        String exceptionMessage = bundle.getString("auth.invalid_otp");
        InvalidOtpException invalidOtpException = new InvalidOtpException();

        ResponseEntity<RestErrorMessage> responseEntity = authExceptionsHandler
                .handleInvalidOtpException(invalidOtpException);

        // Assert that the response status is UNAUTHORIZED and the message is the expected one
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals(exceptionMessage, invalidOtpException.getMessage());
    }
}