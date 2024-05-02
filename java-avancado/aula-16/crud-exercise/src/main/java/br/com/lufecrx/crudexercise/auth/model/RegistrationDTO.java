package br.com.lufecrx.crudexercise.auth.model;

import java.time.LocalDate;

public record RegistrationDTO(
        String login,
        String password,
        String email,
        LocalDate birthDate,
        String mobilePhone,
        UserRole role) {

}
