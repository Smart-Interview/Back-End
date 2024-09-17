package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.candidate.CandidateResponse;
import com.ilyaselmabrouki.application_service.offer.OfferResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ApplicationResponse {
    private Integer id;
    private OfferResponse offer;
    private Integer cvId;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
}
