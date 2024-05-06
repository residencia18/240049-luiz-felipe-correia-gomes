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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/password")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @Operation(summary = "Request a password reset", description = "Request a password reset for the given email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password reset requested successfully"),
            @ApiResponse(responseCode = "404", description = "User not found with the given email"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestBody @Valid PasswordResetRequestDTO data) {
        passwordResetService.requestReset(data);
        return ResponseEntity.ok(bundle.getString("password_reset.requested"));
    }

    @Operation(summary = "Reset a password", description = "Reset a password for the given email and token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Password reset successfully"),
        @ApiResponse(responseCode = "400", description = "OTP and new password must be provided"),
        @ApiResponse(responseCode = "401", description = "Invalid or expired OTP"),        
        @ApiResponse(responseCode = "404", description = "User not found with the given email"),
    })
    @PostMapping("/reset")
    public ResponseEntity<String> reset(
            @RequestParam String email,
            @RequestParam String token,
            @RequestBody @Valid PasswordResetDTO data) {
        passwordResetService.reset(email, token, data);
        return ResponseEntity.ok(bundle.getString("password_reset.successful"));
    }
}
