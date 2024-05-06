package br.com.lufecrx.crudexercise.auth.dashboard;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/profile/dashboard")
public class DashboardController {

    @Operation(summary = "Test authentication", description = "Test if the user is authenticated")
    @GetMapping
    public String dashboard() {
        String username = "Guest"; // Default username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }
        return "Hello " + username;
    }
}