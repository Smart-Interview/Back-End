package com.ilyaselmabrouki.test_service.test;

import com.ilyaselmabrouki.test_service.candidate.CandidateResponse;
import com.ilyaselmabrouki.test_service.offer.OfferResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TestResponse {
    private Integer id;
    private OfferResponse offer;
    private CandidateResponse candidate;
    private Double score;
    private LocalDateTime createdAt;
}
