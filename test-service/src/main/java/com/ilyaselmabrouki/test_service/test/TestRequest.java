package com.ilyaselmabrouki.test_service.test;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestRequest {
    private Integer id;
    @NotNull(message = "Candidate should be present")
    private Integer candidateId;
    @NotNull(message = "Offer should be present")
    private Integer offerId;
    @NotNull(message = "Application should be present")
    private Integer applicationId;
}
