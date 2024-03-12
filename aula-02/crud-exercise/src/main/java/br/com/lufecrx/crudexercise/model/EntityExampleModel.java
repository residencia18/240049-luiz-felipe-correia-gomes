package br.com.lufecrx.crudexercise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entity_examples")
public class EntityExampleModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name example cannot be blank")
    private String nameExample;

    // Method to verify if the model is valid
    public boolean nameExampleIsValid() {
        return nameExample != null && nameExample.length() > 2;
    }
}
