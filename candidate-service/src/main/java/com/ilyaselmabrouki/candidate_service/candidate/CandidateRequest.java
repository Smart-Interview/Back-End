package com.ilyaselmabrouki.candidate_service.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CandidateRequest {
    private Integer id;
    @NotNull(message = "Candidate first name is required")
    private String firstName;
    @NotNull(message = "Candidate last name is required")
    private String lastName;
    @NotNull(message = "Candidate email is required")
    @Email(message = "Candidate email is not a valid email address")
    private String email;
}
