package br.com.lufecrx.crudexercise.auth.infra.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.lufecrx.crudexercise.auth.model.User;
import lombok.extern.slf4j.Slf4j;

// This service is responsible for handling JWT token operations
@Service
@Slf4j
public class TokenService {

    // Secret key for signing the JWT token, fetched from application properties
    @Value("${auth.security.token.secret}")
    private String secret;

    // Expiration time for the JWT token, fetched from application properties
    @Value("${auth.security.token.expiration-time}")
    private long EXPIRATION_TIME;

    // Method to generate a JWT token for a given user
    public String generateToken(User user) {
        try {
            // Use HMAC256 algorithm for signing the token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var now = Instant.now();
            // Create a new JWT token with issuer, subject (user login), and expiration time
            String token = JWT.create()
                    .withIssuer("auth-service")
                    .withSubject(user.getLogin())
                    .withExpiresAt(now.plusSeconds(EXPIRATION_TIME))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            log.error("Error while generating token");
            throw new RuntimeException("Error while generating token");
        }
    }

    // Method to validate a given JWT token
    public String validateToken(String token) {
        try {
            // Use HMAC256 algorithm for verifying the token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // Create a JWT verifier with the same issuer
            var verifier = JWT.require(algorithm)
                    .withIssuer("auth-service")
                    .build();
            // Verify the token and decode it to get the subject (user login)
            var decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

}
