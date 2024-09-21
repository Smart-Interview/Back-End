package com.ilyaselmabrouki.report_service.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TestResponse {
    private Integer id;
    private CandidateResponse candidate;
    private Double score;
    private LocalDateTime createdAt;
}
