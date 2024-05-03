package br.com.lufecrx.crudexercise.exceptions.global.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import br.com.lufecrx.crudexercise.exceptions.global.message.RestErrorMessage;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, errors.toString());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorMessage> handleConstraintViolationException(ConstraintViolationException ex,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, errors.toString());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RestErrorMessage> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode httpStatusCode = ex.getStatusCode();
        HttpStatus httpStatus = HttpStatus.valueOf(httpStatusCode.value());
        RestErrorMessage threatResponse = new RestErrorMessage(httpStatus, ex.getReason());
        return ResponseEntity.status(httpStatus).body(threatResponse);
    }

}