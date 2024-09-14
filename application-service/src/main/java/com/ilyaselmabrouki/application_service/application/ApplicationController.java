package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.report.ApplicationReponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> createApplication(
            @ModelAttribute @Valid ApplicationRequest request
    ) throws IOException {
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

    @GetMapping("/candidate")
    public ResponseEntity<List<ApplicationResponse>> findApplicationsByCandidateId(
            @RequestParam Integer candidateId
    ){
        return ResponseEntity.ok(service.findApplicationsByCandidateId(candidateId));
    }

    @GetMapping("/offer")
    public ResponseEntity<List<ApplicationReponse>> findApplicationsByOfferId(@RequestParam Integer offerId){
        return ResponseEntity.ok(service.findCandidatesByOfferIdAndStatus(offerId));
    }

}
