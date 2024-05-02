package br.com.lufecrx.crudexercise.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lufecrx.crudexercise.auth.model.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.model.User;
import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.LoginAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    // Method to authenticate a user with a username and password
    public void login(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        authenticationManager.authenticate(usernamePassword);
    }

    // Method to register a new user
    public void signup(RegistrationDTO data) {
        log.info("Registering a new user with login: {}", data.login());

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

        log.info("User created: {}", user);

        // Save the new user to the database
        userRepository.save(user);
    }
}
