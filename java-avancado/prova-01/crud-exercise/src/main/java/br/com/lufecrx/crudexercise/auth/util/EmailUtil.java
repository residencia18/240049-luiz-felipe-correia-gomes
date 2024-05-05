package br.com.lufecrx.crudexercise.auth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

// Utility class to handle email sending
@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    // Send email with OTP
    public void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Verify your account");
        helper.setText(
                        """
                        <div>
                        <a href="https://localhost:8080/auth/verify-account?email=%s&token=%s">
                        Click here to verify your account
                        </a>
                        </div>
                        """
                        .formatted(email, otp),
                true);

        mailSender.send(message);
    }

    // Send email to recover password
    public void sendRecoverPasswordEmail(String email, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Recover your password");
        helper.setText(
                """
                        <div>
                        <a href="http://localhost:8080/password/reset?email=%s&token=%s">
                        Click here to set a new password
                        </a>
                        </div>
                        """
                        .formatted(email, otp),
                true);
        
        mailSender.send(message);        
    }
}
