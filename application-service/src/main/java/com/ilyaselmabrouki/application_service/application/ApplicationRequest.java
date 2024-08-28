package com.ilyaselmabrouki.application_service.application;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationRequest {
    private Integer id;
    @NotNull(message = "Candidate should be present")
    private Integer candidateId;
    @NotNull(message = "Offer should be present")
    private Integer offerId;
    @NotNull(message = "CV should be present")
    private String cvPath;
    private ApplicationStatus status;
}
