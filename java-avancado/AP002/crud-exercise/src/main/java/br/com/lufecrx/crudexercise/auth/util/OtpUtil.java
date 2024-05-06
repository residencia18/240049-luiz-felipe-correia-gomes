package br.com.lufecrx.crudexercise.auth.util;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Component;

import br.com.lufecrx.crudexercise.auth.model.OneTimePassword;

// Utility class to generate and validate One Time Passwords
@Component
public class OtpUtil {

    private final int MINUTES_TO_EXPIRE = 5;

    // Generates a random 6-digit OTP
    public OneTimePassword generateOtp() {
        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(1000000));
        return new OneTimePassword(otp, LocalDateTime.now());
    }

    // Checks if the OTP is valid
    public boolean isValidOtp(OneTimePassword otp) {
        return otp.otpGenerationTime().plusMinutes(MINUTES_TO_EXPIRE).isAfter(LocalDateTime.now());
    }
}
