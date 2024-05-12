package br.com.lufecrx.crudexercise.auth.dashboard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDashboardAuthenticated() throws Exception {
        String username = "TestUser";

        // Mocking the authentication to simulate an authenticated user
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn(username);
        when(auth.isAuthenticated()).thenReturn(true);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Expecting the authenticated username
        mockMvc.perform(get("/profile/dashboard"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello " + username));
    }

    @Test
    public void testDashboardUnauthenticated() throws Exception {
        // Removing the authentication to simulate an unauthenticated user
        SecurityContextHolder.getContext().setAuthentication(null);

        // Expecting the default username
        mockMvc.perform(get("/profile/dashboard"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Guest"));
    }
}