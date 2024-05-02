package br.com.lufecrx.crudexercise.auth.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.auth.model.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data) {
        authService.login(data);
        return ResponseEntity.ok(bundle.getString("user.successfully_logged_in"));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid RegistrationDTO data) {
        log.info("Received data to signup: {}", data);
        authService.signup(data);
        return ResponseEntity.ok(bundle.getString("user.successfully_signed_up"));
    }
}
