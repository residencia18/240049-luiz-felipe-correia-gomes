package br.com.lufecrx.crudexercise.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.github.javafaker.Faker;

import br.com.lufecrx.crudexercise.auth.infra.security.TokenService;
import br.com.lufecrx.crudexercise.auth.model.User;
import br.com.lufecrx.crudexercise.auth.model.UserRole;
import br.com.lufecrx.crudexercise.auth.model.dto.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.LoginResponseDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import br.com.lufecrx.crudexercise.auth.services.AuthenticationService;

public class UserAuthenticationAndAuthorizationTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    private Faker faker;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
    }

    @Test
    public void testLogin() {
        String fakeLogin = faker.funnyName().name();
        String fakePassword = faker.internet().password();
        AuthenticationDTO data = new AuthenticationDTO(fakeLogin, fakePassword);
        User user = new User();
        user.setLogin(data.login());
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        String token = "testToken";
        LoginResponseDTO expectedResponse = new LoginResponseDTO(token);

        // Mocking the authenticationManager to return a predefined auth object when
        // authenticate method is called with any argument
        when(authenticationManager.authenticate(any())).thenReturn(auth);

        // Mocking the tokenService to return a predefined token when generateToken
        // method is called with any argument
        when(tokenService.generateToken(any())).thenReturn(token);

        // Calling the login method of the authenticationService with the test data
        LoginResponseDTO actualResponse = authenticationService.login(data);

        // Asserting that the response from the login method is as expected
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testSignup() {
        String fakeLogin = faker.funnyName().name();
        String fakePassword = faker.internet().password();
        String fakeEmail = faker.internet().emailAddress();

        // Creating a RegistrationDTO object with the fake data
        RegistrationDTO data = new RegistrationDTO(fakeLogin, fakePassword, fakeEmail, null, null);

        // Building a User object from the RegistrationDTO
        User user = User.builder()
                .login(data.login())
                .password(data.password())
                .email(data.email())
                .birthDate(data.birthDate())
                .mobilePhone(data.mobilePhone())
                .role(UserRole.USER)
                .build();

        // Mocking the userRepository to return false when existsByLogin or
        // existsByEmail methods are called with any string
        when(userRepository.existsByLogin(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);

        // Calling the signup method of the authenticationService with the test data
        authenticationService.signup(data);

        // Verifying that the save method of the userRepository is called exactly once
        // with an argument that matches the User object
        verify(userRepository, times(1)).save(argThat(new ArgumentMatcher<User>() {
            @Override
            public boolean matches(User argument) {
                return argument.getLogin().equals(user.getLogin()) // Login should be the same
                        && argument.getPassword() != null
                        && !argument.getPassword().equals(fakePassword) // Password should be encrypted
                        && argument.getEmail().equals(user.getEmail()); // Email should be the same
            }
        }));
    }
}