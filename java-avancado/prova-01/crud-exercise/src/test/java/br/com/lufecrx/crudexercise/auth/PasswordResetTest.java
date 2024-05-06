package br.com.lufecrx.crudexercise.auth;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import br.com.lufecrx.crudexercise.auth.model.OneTimePassword;
import br.com.lufecrx.crudexercise.auth.model.User;
import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetDTO;
import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import br.com.lufecrx.crudexercise.auth.services.PasswordResetService;
import br.com.lufecrx.crudexercise.auth.util.OtpUtil;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidOtpException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotFoundException;

public class PasswordResetTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OtpUtil otpUtil;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private PasswordResetService passwordResetService;

    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Create a new user
        user = new User();
        String email = "test@test.com";
        String newPassword = "newPassword";
        OneTimePassword otp = new OneTimePassword("validToken", LocalDateTime.now()); // Create a valid OTP

        user.setEmail(email);
        user.setPassword(newPassword);
        user.setOtp(otp);

        // Save the user in the repository
        when(userRepository.save(any(User.class))).thenReturn(user);
    }

    @Test
    public void testResetWithValidOtp() {
        // Mocking the behavior of userRepository.findByEmail() to return the user
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // Mocking the isValidOtp method to return true, indicating a valid OTP
        when(otpUtil.isValidOtp(any(OneTimePassword.class))).thenReturn(true);

        // Mocking the behavior of the bCryptPasswordEncoder.encode() method
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encryptedPassword");

        // Providing a valid OTP token for the test
        String validOtpToken = "validToken";

        // Invoking the reset method with the valid OTP token
        passwordResetService.reset(user.getEmail(), validOtpToken, new PasswordResetDTO("newPassword"));

        // Verifying that the userRepository.save() method is called once
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testResetWithNullToken() {
        String email = "test@test.com";
        String newPassword = "newPassword";

        // Invoking the reset method with a null token to test the exception
        assertThrows(ResponseStatusException.class,
                () -> passwordResetService.reset(email, null, new PasswordResetDTO(newPassword)));
    }

    @Test
    public void testResetWithNullNewPassword() {
        String email = "test@test.com";
        String token = "123456";
 
        // Invoking the reset method with a null new password to test the exception
        assertThrows(ResponseStatusException.class, () -> passwordResetService.reset(email, token, null));
    }

    @Test
    public void testResetWithInvalidOtp() {
        // Providing an invalid OTP token for the test
        String email = "test@test.com";
        String token = "123456";
        String newPassword = "newPassword";
        OneTimePassword otp = new OneTimePassword(token, LocalDateTime.now());

        // Mocking the behavior of the userRepository.findByEmail() method to return the user
        User user = new User();
        user.setOtp(otp);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(otpUtil.isValidOtp(user.getOtp())).thenReturn(false);

        // Invoking the reset method with an invalid OTP token to test the exception
        assertThrows(InvalidOtpException.class,
                () -> passwordResetService.reset(email, token, new PasswordResetDTO(newPassword)));
    }

    @Test
    public void testResetWithUserNotFound() {
        // Providing an email that does not exist in the repository
        String email = "test@test.com";
        String token = "123456";
        String newPassword = "newPassword";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Invoking the reset method with an email that does not exist in the repository to test the exception
        assertThrows(UserNotFoundException.class,
                () -> passwordResetService.reset(email, token, new PasswordResetDTO(newPassword)));
    }
}