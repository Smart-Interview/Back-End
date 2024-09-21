package com.ilyaselmabrouki.test_service.candidate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "candidate-service",
        url = "${application.config.candidate-url}"
)
public interface CandidateClient {
    @GetMapping("/{id}")
    Optional<CandidateResponse> findCandidateById(@PathVariable("id") Integer id);

    @PostMapping("/ids")
    List<CandidateResponse> findCandidatesByIds(@RequestBody List<Integer> candidateIds);
}
