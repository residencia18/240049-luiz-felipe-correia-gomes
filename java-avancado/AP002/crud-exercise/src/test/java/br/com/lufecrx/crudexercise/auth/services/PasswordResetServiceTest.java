package br.com.lufecrx.crudexercise.auth.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

import br.com.lufecrx.crudexercise.auth.model.OneTimePassword;
import br.com.lufecrx.crudexercise.auth.model.User;
import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetRequestDTO;
import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import br.com.lufecrx.crudexercise.auth.util.EmailUtil;
import br.com.lufecrx.crudexercise.auth.util.OtpUtil;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidOtpException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotFoundException;

public class PasswordResetServiceTest {

    @InjectMocks
    private PasswordResetService passwordResetService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailUtil emailUtil;

    @Mock
    private OtpUtil otpUtil;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRequestReset() throws Exception {
        PasswordResetRequestDTO data = new PasswordResetRequestDTO("test@test.com");
        User user = new User();
        user.setEmail(data.email());

        // Mocking the behavior of the methods that will be called in the service method
        when(userRepository.findByEmail(data.email())).thenReturn(Optional.of(user));
        when(otpUtil.generateOtp()).thenReturn(new OneTimePassword("123456", LocalDateTime.now()));

        // Requesting the password reset
        passwordResetService.requestReset(data);

        // Verifying if the methods were called
        verify(userRepository, times(1)).findByEmail(data.email());
        verify(userRepository, times(1)).save(user);
        verify(emailUtil, times(1)).sendRecoverPasswordEmail(data.email(), user.getOtp().otp());
    }

    @Test
    public void testReset() {
        OneTimePassword otp = new OneTimePassword("123456", LocalDateTime.now());
        String email = "test@test.com";
        String token = "123456";

        PasswordResetDTO data = new PasswordResetDTO("newPassword");
        User user = new User();
        user.setEmail(email);
        user.setOtp(otp);

        // Mocking the behavior of the methods that will be called in the service method
        when(otpUtil.isValidOtp(otp)).thenReturn(true);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Resetting the password
        passwordResetService.reset(email, token, data);

        // Verifying if the methods were called
        verify(userRepository, times(1)).findByEmail(email);
        verify(otpUtil, times(1)).isValidOtp(otp);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testResetInvalidOtp() {
        String email = "test@test.com";
        String token = "123456";
        PasswordResetDTO data = new PasswordResetDTO("newPassword");
        User user = new User();
        user.setEmail(email);
        user.setOtp(new OneTimePassword("654321", LocalDateTime.now()));

        // Mocking the behavior of the methods that will be called in the service method
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(otpUtil.isValidOtp(user.getOtp())).thenReturn(false);

        // Resetting the password with an invalid OTP token
        assertThrows(InvalidOtpException.class, () -> passwordResetService.reset(email, token, data));
    }

    @Test
    public void testResetUserNotFound() {
        String email = "test@test.com";
        String token = "123456";
        PasswordResetDTO data = new PasswordResetDTO("newPassword");

        // Mocking the behavior of the methods that will be called in the service method
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Resetting the password with an invalid OTP token
        assertThrows(UserNotFoundException.class, () -> passwordResetService.reset(email, token, data));
    }
}