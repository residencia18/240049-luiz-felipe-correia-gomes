package br.com.lufecrx.crudexercise.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetRequestDTO;
import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import br.com.lufecrx.crudexercise.auth.util.EmailUtil;
import br.com.lufecrx.crudexercise.auth.util.OtpUtil;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.authentication.InvalidOtpException;
import br.com.lufecrx.crudexercise.exceptions.auth.domain.user.UserNotFoundException;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PasswordResetService {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private UserRepository userRepository;

    // Method to request a password reset
    public void requestReset(PasswordResetRequestDTO data) {
        log.info("Received data to request a password reset");

        // Find user by email
        var user = userRepository.findByEmail(data.email())
                .orElseThrow(() -> new UserNotFoundException(data.email()));

        // Generate OTP to reset password and set it to the user
        var oneTimePassword = otpUtil.generateOtp();
        user.setOtp(oneTimePassword);

        // Send email to reset password
        try {
            emailUtil.sendRecoverPasswordEmail(data.email(), oneTimePassword.otp());
        } catch (MessagingException e) {
            log.error("Error occurred while sending email to reset password");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while sending email to reset password");
        }
    }

    // Method to reset a password
    public void reset(String email, String token, PasswordResetDTO data) {
        log.info("Received data to reset a password");

        if (token == null || data.newPassword() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP and new password must be provided");

        // Find user by email 
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        // Check if the OTP is valid and matches the user's, then reset the password
        if (user.getOtp().otp().equals(token) && otpUtil.isValidOtp(user.getOtp())) {
            throw new InvalidOtpException(new Throwable("Invalid or expired OTP"));
        } else {
            // Encrypt new password and update user's password
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.newPassword());
            user.setPassword(encryptedPassword);
            user.setOtp(null); // Remove the OTP
            userRepository.save(user);
        }
    }
}
