package br.com.lufecrx.crudexercise.audit.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "EventName cannot be null or empty")
    @Size(min = 5, max = 50, message = "EventName must be between 5 and 50 characters")
    @Column(unique = true, nullable = false)
    private String eventName;
    private String eventDescription;
    private Date timestamp;
    private String userId;
    private String affectedResource;
    private String origin;
}