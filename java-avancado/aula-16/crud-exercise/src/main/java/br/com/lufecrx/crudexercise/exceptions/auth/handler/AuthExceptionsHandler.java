package br.com.lufecrx.crudexercise.exceptions.auth.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthExceptionsHandler extends ResponseEntityExceptionHandler {

    // @ExceptionHandler(InternalErrorAuthException.class)
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public ResponseEntity<RestErrorMessage> handleInternalErrorAuthException(InternalErrorAuthException ex) {
    //     RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    // }

    // @ExceptionHandler(InvalidArgumentProvidedException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ResponseEntity<RestErrorMessage> handleInvalidArgumentProvidedException(InvalidArgumentProvidedException ex) {
    //     RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    // }

    // @ExceptionHandler(ResourceDeniedException.class)
    // @ResponseStatus(HttpStatus.FORBIDDEN)
    // public ResponseEntity<RestErrorMessage> handleResourceDeniedException(ResourceDeniedException ex) {
    //     RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.FORBIDDEN, ex.getMessage());
    //     return ResponseEntity.status(HttpStatus.FORBIDDEN).body(threatResponse);
    // }
}
