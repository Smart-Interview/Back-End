package com.elabidisoufiane.sosouca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CEORequest {
    private Integer id;
    @NotNull(message = "Candidate userName is required")
    private String userName;
    @NotNull(message = "Candidate email is required")
    @Email(message = "Candidate email is not a valid email address")
    private String email;
}
