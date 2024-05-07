package br.com.lufecrx.crudexercise.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.lufecrx.crudexercise.auth.controller.AuthenticationController;
import br.com.lufecrx.crudexercise.auth.model.dto.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.LoginResponseDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.services.AuthenticationService;

public class AuthenticationTest {

    @InjectMocks
    AuthenticationController controller;

    @Mock
    AuthenticationService authService;

    private final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    // This test checks if the login is successful
    @Test
    public void testLoginSuccess() {
        AuthenticationDTO authDTO = new AuthenticationDTO("testUser", "testPassword");

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO("testToken");

        // When the login method of the authService is called with the mock authDTO,
        // return the mock loginResponseDTO
        when(authService.login(authDTO)).thenReturn(loginResponseDTO);

        // Call the login method of the controller with the mock authDTO
        ResponseEntity<LoginResponseDTO> responseEntity = controller.login(authDTO);

        // Check if the status code of the response is 200 and the token is the expected
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals("testToken", responseEntity.getBody().token());
    }

    // This test checks if the login fails when the password is wrong
    @Test
    public void testLoginFailure() {
        AuthenticationDTO authDTO = new AuthenticationDTO("testUser", "wrongPassword");

        // When the login method of the authService is called with the mock authDTO,
        // return null to simulate login failure
        when(authService.login(authDTO)).thenReturn(null);

        // Call the login method of the controller with the mock authDTO
        ResponseEntity<LoginResponseDTO> responseEntity = controller.login(authDTO);

        // Check if the status code of the response is 200 and the token is null
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testSignupWithValidData() {
        // Create a RegistrationDTO object with valid data
        RegistrationDTO regDTO = new RegistrationDTO("validUser", "validPassword", "validEmail", null, null);

        // Mock the authentication service to do nothing when the signup method is
        // called with regDTO
        doNothing().when(authService).signup(regDTO);

        // Call the signup method of the controller with regDTO
        ResponseEntity<String> responseEntity = controller.signup(regDTO);

        // Assert that the status code of the response is 200
        assertEquals(200, responseEntity.getStatusCode().value());
        // Assert that the response message is as expected
        assertEquals(bundle.getString("user.successfully_signed_up"), responseEntity.getBody());
    }

    @Test
    public void testVerifyAccountWithValidData() {
        // Define valid email and token
        String email = "validEmail";
        String token = "validToken";

        // Mock the authentication service to do nothing when the verifyAccount method
        // is called with email and token
        doNothing().when(authService).verifyAccount(email, token);

        // Call the verifyAccount method of the controller with email and token
        ResponseEntity<String> responseEntity = controller.verifyAccount(email, token);

        // Assert that the status code of the response is 200
        assertEquals(200, responseEntity.getStatusCode().value());
        // Assert that the response message is as expected
        assertEquals(bundle.getString("user.successfully_verified"), responseEntity.getBody());
    }

    @Test
    public void testResendVerificationWithValidEmail() {
        // Define a valid email
        String email = "validEmail";

        // Mock the authentication service to do nothing when the resendVerification
        // method is called with email
        doNothing().when(authService).resendVerification(email);

        // Call the resendVerification method of the controller with email
        ResponseEntity<String> responseEntity = controller.resendVerification(email);

        // Assert that the status code of the response is 200
        assertEquals(200, responseEntity.getStatusCode().value());
        // Assert that the response message is as expected
        assertEquals(bundle.getString("user.verification_email_resent"), responseEntity.getBody());
    }
}