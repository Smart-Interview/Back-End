package com.ilyaselmabrouki.test_service.candidate;

import lombok.Data;

@Data
public class CandidateResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
