package com.ilyaselmabrouki.application_service.report;

import com.ilyaselmabrouki.application_service.application.ApplicationStatus;
import com.ilyaselmabrouki.application_service.candidate.CandidateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationReponse {
    private ApplicationStatus status;  // e.g., 'UNDER REVIEW', 'ACCEPTED', 'REJECTED'
    private Integer count;  // Number of applications with this status
    private List<CandidateResponse> candidates;  // Candidate details for this status
}
