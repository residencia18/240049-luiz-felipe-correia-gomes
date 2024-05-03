package br.com.lufecrx.crudexercise.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.lufecrx.crudexercise.auth.infra.security.TokenService;
import br.com.lufecrx.crudexercise.auth.model.User;
import br.com.lufecrx.crudexercise.auth.model.dto.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.LoginResponseDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidCredentialsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.LoginAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    // Method to authenticate a user with a username and password
    public LoginResponseDTO login(AuthenticationDTO data) {
        log.info("Received data to login");

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            LoginResponseDTO response = new LoginResponseDTO(token);

            log.info("User logged in: {}", response);

            return response;
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException();
        } catch (Exception ex) {
            log.error("Error occurred during login");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred during login");
        }
    }

    // Method to register a new user
    public void signup(RegistrationDTO data) {
        log.info("Received data to signup");

        // Check if a user with the same login already exists
        if (userRepository.existsByLogin(data.login())) {
            throw new LoginAlreadyExistsException(data.login());
        }

        // Check if a user with the same email already exists
        if (userRepository.existsByEmail(data.email())) {
            throw new EmailAlreadyExistsException(data.email());
        }

        // Encrypt the user's password
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        // Create a new User object with the provided data
        User user = User.builder()
                .login(data.login())
                .password(encryptedPassword)
                .email(data.email())
                .birthDate(data.birthDate())
                .mobilePhone(data.mobilePhone())
                .role(data.role())
                .build();

        log.info("New user created: {}", data.login());

        // Save the new user to the database
        userRepository.save(user);
    }
}
