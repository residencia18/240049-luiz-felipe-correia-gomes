package br.com.lufecrx.crudexercise.auth.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // .cors(Customizer.withD/efaults()) // Cross-Origin Resource Sharing (CORS) is a security concept that allows restricting the resources implemented in web browsers.
                .csrf(crsf -> crsf.disable()) // CSRF (Cross-Site Request Forgery) is an attack that forces an end user to execute unwanted actions on a web application in which they're currently authenticated.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Session management is a process by which a server maintains the state of an entity interacting with it.
                .build();
    }
}
