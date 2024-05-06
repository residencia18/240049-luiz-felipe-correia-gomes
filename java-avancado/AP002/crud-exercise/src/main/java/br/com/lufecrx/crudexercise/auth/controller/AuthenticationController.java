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

import br.com.lufecrx.crudexercise.auth.model.dto.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.LoginResponseDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @Operation(summary = "Authenticate a user", description = "Authenticate a user with the given credentials and return a token if successful")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully with the given credentials and a token is returned"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials provided"),
            @ApiResponse(responseCode = "400", description = "User is not verified")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var response = authService.login(data);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Sign up a new user", description = "Sign up a new user with the given data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed up successfully"),
            @ApiResponse(responseCode = "400", description = "Email or login already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid RegistrationDTO data) {
        authService.signup(data);
        return ResponseEntity.ok(bundle.getString("user.successfully_signed_up"));
    }

    @Operation(summary = "Verify a user's email", description = "Verify a user's email with the given email and token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's email verified successfully"),
            @ApiResponse(responseCode = "400", description = "User already verified"),
            @ApiResponse(responseCode = "401", description = "Invalid token provided"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String email, @RequestParam String token) {
        authService.verifyAccount(email, token);
        return ResponseEntity.ok(bundle.getString("user.successfully_verified"));
    }

    @Operation(summary = "Resend verification email", description = "Resend the verification email to the user with the given email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verification email resent successfully"),
            @ApiResponse(responseCode = "400", description = "User already verified"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/resend-verification")
    public ResponseEntity<String> resendVerification(@RequestParam String email) {
        authService.resendVerification(email);
        return ResponseEntity.ok(bundle.getString("user.verification_email_resent"));
    }
}
