package com.ilyaselmabrouki.test_service.application;

import lombok.Data;

@Data
public class ApplicationResponse {
    private Integer id;
    private Integer candidateId;
    private Integer offerId;
    private String cvPath;
    private String status;
}
