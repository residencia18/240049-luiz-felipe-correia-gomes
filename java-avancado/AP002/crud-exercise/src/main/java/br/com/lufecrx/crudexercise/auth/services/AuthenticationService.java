package br.com.lufecrx.crudexercise.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.lufecrx.crudexercise.auth.infra.security.TokenService;
import br.com.lufecrx.crudexercise.auth.model.OneTimePassword;
import br.com.lufecrx.crudexercise.auth.model.User;
import br.com.lufecrx.crudexercise.auth.model.dto.AuthenticationDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.LoginResponseDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.RegistrationDTO;
import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import br.com.lufecrx.crudexercise.auth.util.EmailUtil;
import br.com.lufecrx.crudexercise.auth.util.OtpUtil;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidCredentialsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidOtpException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.EmailAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.LoginAlreadyExistsException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserAlreadyVerified;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotEnabledException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotFoundException;
import jakarta.mail.MessagingException;
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

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private OtpUtil otpUtil;

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
        } catch (DisabledException ex) {
            throw new UserNotEnabledException();
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

        // Send an email to the user with the OTP
        OneTimePassword oneTimePassword = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(data.email(), oneTimePassword.otp());
        } catch (MessagingException ex) {
            log.error("Error occurred while sending email to verify account");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while sending email to verify account");
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
                .otp(oneTimePassword)
                .build();

        log.info("New user created: {}", data.login());

        // Save the new user to the database
        userRepository.save(user);
    }

    // Method to verify a user's account with an OTP
    public void verifyAccount(String email, String otp) {
        log.info("Received email and OTP to verify account");

        // Check if the user exists, if not throw an exception
        userRepository.findByEmail(email)
                .ifPresentOrElse(user -> {
                    log.info("User found: {}", user.getLogin());
                    // Check if the user's account is already enabled
                    if (user.isEnabled()) {
                        throw new UserAlreadyVerified();
                    }

                    // Check if the OTP is valid and matches the user's, then enable the user's
                    // account
                    if (user.getOtp().otp().equals(otp) && otpUtil.isValidOtp(user.getOtp())) {
                        user.setEnabled(true);
                        user.setOtp(null); // Remove the OTP
                        userRepository.save(user);
                        log.info("User account verified");
                    } else {
                        throw new InvalidOtpException(new Throwable("Invalid or expired OTP"));
                    }
                }, () -> {
                    throw new UserNotFoundException(email);
                });
    }

    // Method to resend the verification email
    public void resendVerification(String email) {
        log.info("Received email to resend verification email");

        // Check if the user exists, if not throw an exception
        userRepository.findByEmail(email)
                .ifPresentOrElse(user -> {
                    log.info("User found: {}", user.getLogin());
                    // Check if the user's account is already enabled
                    if (user.isEnabled()) {
                        throw new UserAlreadyVerified();
                    }

                    // Send an email to the user with a new OTP
                    OneTimePassword oneTimePassword = otpUtil.generateOtp();
                    try {
                        emailUtil.sendOtpEmail(email, oneTimePassword.otp());
                    } catch (MessagingException ex) {
                        log.error("Error occurred while sending email to verify account");
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                "Error occurred while sending email to verify account");
                    }
                    user.setOtp(oneTimePassword); // Update the user's OTP
                    userRepository.save(user);
                }, () -> {
                    throw new UserNotFoundException(email);
                });
    }

}
