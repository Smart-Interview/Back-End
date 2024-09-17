package com.ilyaselmabrouki.report_service.application;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationResponse {
    private String status;  // e.g., 'UNDER REVIEW', 'ACCEPTED', 'REJECTED'
    private Integer count;  // Number of applications with this status
    private List<CandidateResponse> candidates;  // Candidate details for this status
}
