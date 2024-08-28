package com.ilyaselmabrouki.application_service.candidate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "application-service",
        url = "http://localhost:8070/api/v1/candidates"
)
public interface CandidateClient {
    @GetMapping("/{id}")
    Optional<CandidateResponse> findCandidateById(@PathVariable("id") Integer id);
}
