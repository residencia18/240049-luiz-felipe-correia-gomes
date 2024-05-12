package br.com.lufecrx.crudexercise.auth.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetDTO;
import br.com.lufecrx.crudexercise.auth.model.dto.PasswordResetRequestDTO;
import br.com.lufecrx.crudexercise.auth.services.PasswordResetService;

public class PasswordResetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PasswordResetService passwordResetService;

    @InjectMocks
    private PasswordResetController passwordResetController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(passwordResetController).build();
    }

    @Test
    public void requestResetTest() throws Exception {
        PasswordResetRequestDTO passwordResetRequestDTO = new PasswordResetRequestDTO("test@test.com");

        mockMvc.perform(post("/password/request-reset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(passwordResetRequestDTO)))
                .andExpect(status().isOk());

        verify(passwordResetService, times(1)).requestReset(passwordResetRequestDTO);
    }

    @Test
    public void resetPasswordTest() throws Exception {
        PasswordResetDTO passwordResetDTO = new PasswordResetDTO("newPassword");

        mockMvc.perform(post("/password/reset")
                .param("email", "test@test.com")
                .param("token", "token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(passwordResetDTO)))
                .andExpect(status().isOk());

        verify(passwordResetService, times(1)).reset("test@test.com", "token", passwordResetDTO);
    }

}