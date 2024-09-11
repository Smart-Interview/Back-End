package com.ilyaselmabrouki.test_service.test;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResultRequest {
    @NotNull(message = "Test answer should be present")
    private String testAnswer;
    @NotNull(message = "Candidate answer should be present")
    private String candidateAnswer;
}
