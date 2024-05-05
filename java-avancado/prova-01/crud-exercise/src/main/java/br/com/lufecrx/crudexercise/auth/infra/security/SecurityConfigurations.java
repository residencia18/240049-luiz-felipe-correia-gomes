package br.com.lufecrx.crudexercise.auth.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Enable CORS with default configuration
                .cors(Customizer.withDefaults())

                // Disable CSRF protection as our service is stateless and doesn't use cookies
                .csrf(crsf -> crsf.disable())

                // Make the session stateless, as we are using JWTs for authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize -> authorize
                        // Allow access to the Swagger UI for all users
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        // Allow POST requests to /auth/signup for all users
                        .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                        // Allow POST requests to /auth/login for all users
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // Allow POST requests to /verify-account for all users
                        .requestMatchers(HttpMethod.POST, "/auth/verify-account").permitAll()
                        // Allow POST requests to /resend-verification?email= for all users
                        .requestMatchers(HttpMethod.POST, "/auth/resend-verification").permitAll()
                        // Allow POST requests to /request-reset for all users
                        .requestMatchers(HttpMethod.POST, "/request-reset").permitAll()
                        // Allow POST requests to /reset for all users
                        .requestMatchers(HttpMethod.POST, "/reset").permitAll()
                        // Allow GET requests to /categories/** and /products/** for all users
                        .requestMatchers(HttpMethod.GET, "/categories/**", "/products/**").permitAll()
                        // Require authentication for all GET, POST, PUT, DELETE requests to
                        // /wishlists/**
                        .requestMatchers(HttpMethod.GET, "/wishlists/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/wishlists/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/wishlists/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/wishlists/**").authenticated()
                        // Only allow users with the ADMIN role to POST, PUT, DELETE to /products/** and
                        // /categories/**
                        .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")
                        // Require authentication for all other requests
                        .anyRequest().authenticated())

                // Add JWT token filter to the filter chain
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
