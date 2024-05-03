package br.com.lufecrx.crudexercise.auth.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.auth.model.dto.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.LoginResponseDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.services.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    // Endpoint to login a user
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var response = authService.login(data);
        return ResponseEntity.ok(response);
    }

    // Endpoint to signup a user
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid RegistrationDTO data) {
        authService.signup(data);
        return ResponseEntity.ok(bundle.getString("user.successfully_signed_up"));
    }
}
