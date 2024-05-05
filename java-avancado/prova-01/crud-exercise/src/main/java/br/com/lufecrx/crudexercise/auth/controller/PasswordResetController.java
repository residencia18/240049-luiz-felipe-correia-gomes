package br.com.lufecrx.crudexercise.auth.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetRequestDTO;
import br.com.lufecrx.crudexercise.auth.services.PasswordResetService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/password")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    // Endpoint to request a password reset
    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestBody @Valid PasswordResetRequestDTO data) {
        passwordResetService.requestReset(data);
        return ResponseEntity.ok(bundle.getString("password_reset.requested"));
    }

    // Endpoint to reset a password
    @PostMapping("/reset")
    public ResponseEntity<String> reset(
            @RequestParam String email,
            @RequestParam String token,
            @RequestBody @Valid PasswordResetDTO data) {
        passwordResetService.reset(email, token, data);
        return ResponseEntity.ok(bundle.getString("password_reset.successful"));
    }
}
