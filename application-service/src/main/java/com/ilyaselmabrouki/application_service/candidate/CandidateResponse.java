package com.ilyaselmabrouki.application_service.candidate;

import lombok.Data;

@Data
public class CandidateResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
