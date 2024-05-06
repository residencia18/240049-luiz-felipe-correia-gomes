package br.com.lufecrx.crudexercise.exceptions.auth.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.LoginAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserAlreadyVerified;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotEnabledException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotFoundException;
import br.com.lufecrx.crudexercise.exceptions.global.message.RestErrorMessage;

@ControllerAdvice
public class UserExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestErrorMessage> handleUserNotFoundException(UserNotFoundException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(LoginAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleLoginAlreadyExistsException(LoginAlreadyExistsException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UserNotEnabledException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleUserNotEnabledException(UserNotEnabledException ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UserAlreadyVerified.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleUserAlreadyVerified(UserAlreadyVerified ex) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }
}
