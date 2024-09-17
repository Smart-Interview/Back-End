package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.candidate.CandidateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ApplicationResponse {
    private Integer id;
    private Integer candidateId;
    private Integer offerId;
    private Integer cvId;
    private ApplicationStatus status;
    private CandidateResponse candidate;
}
