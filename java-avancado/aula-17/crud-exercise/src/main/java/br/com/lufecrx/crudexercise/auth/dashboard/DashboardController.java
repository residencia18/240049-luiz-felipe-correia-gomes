package br.com.lufecrx.crudexercise.auth.dashboard;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile/dashboard")
public class DashboardController {

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