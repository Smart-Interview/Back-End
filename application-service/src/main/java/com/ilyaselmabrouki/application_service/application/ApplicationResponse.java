package com.ilyaselmabrouki.application_service.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ApplicationResponse {
    private Integer id;
    private Integer candidateId;
    private Integer offerId;
    private String cvPath;
    private ApplicationStatus status;
}
