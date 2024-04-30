package br.com.lufecrx.crudexercise.api.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User name cannot be blank")
    private String name;

    @Email(message = "User email must be valid")
    private String email;

    @Past (message = "User birth date must be in the past")
    private LocalDate birthDate;

    @Pattern(regexp = "^[0-9]{11}$", message = "User mobile phone must have 11 digits")
    private String mobilePhone;
}
