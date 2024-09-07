package com.ilyaselmabrouki.candidate_service.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CandidateResponse {
    private Integer id;
    private String userName;
    private String email;
}
