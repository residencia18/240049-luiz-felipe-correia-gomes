package br.com.lufecrx.crudexercise.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import br.com.lufecrx.crudexercise.auth.services.AuthenticationService;

public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController controller;

    @Mock
    AuthenticationService authService;

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
}