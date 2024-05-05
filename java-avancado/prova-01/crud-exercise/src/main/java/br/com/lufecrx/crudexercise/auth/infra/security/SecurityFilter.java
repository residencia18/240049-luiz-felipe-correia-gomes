package br.com.lufecrx.crudexercise.auth.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.lufecrx.crudexercise.auth.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// This filter is responsible for handling security for each request
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    // This method is called for each request
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = recoverToken(request);
        // If the token exists
        if (token != null) {
            // Validate the token and get the user login
            var login = tokenService.validateToken(token);
            // Fetch the user details from the repository
            UserDetails user = userRepository.findByLogin(login);

            // Create an authentication token and set it in the SecurityContext
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    // Method to recover the token from the request header
    private String recoverToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        // If the header is null, empty, or doesn't start with "Bearer ", return null
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        // Otherwise, remove "Bearer " from the token and return it
        return token.replace("Bearer ", "");
    }

}
