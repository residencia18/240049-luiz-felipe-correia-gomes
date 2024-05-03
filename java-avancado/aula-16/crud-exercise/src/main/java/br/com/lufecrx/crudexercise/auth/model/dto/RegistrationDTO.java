package br.com.lufecrx.crudexercise.auth.model.dto;

import java.time.LocalDate;

import br.com.lufecrx.crudexercise.auth.model.UserRole;

public record RegistrationDTO(
        String login,
        String password,
        String email,
        LocalDate birthDate,
        String mobilePhone,
        UserRole role) {

}
