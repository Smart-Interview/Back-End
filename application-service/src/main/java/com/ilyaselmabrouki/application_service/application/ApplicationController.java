package com.ilyaselmabrouki.application_service.application;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @PostMapping
    public ResponseEntity<Integer> createApplication(
            @RequestBody @Valid ApplicationRequest request
    ){
        return ResponseEntity.ok(service.createApplication(request));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications(){
        return ResponseEntity.ok(service.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> findApplication(@PathVariable Integer id){
        return ResponseEntity.ok(service.findApplication(id));
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<List<ApplicationResponse>> findApplicationsByCandidateId(
            @PathVariable Integer candidateId
    ){
        return ResponseEntity.ok(service.findApplicationsByCandidateId(candidateId));
    }

}
