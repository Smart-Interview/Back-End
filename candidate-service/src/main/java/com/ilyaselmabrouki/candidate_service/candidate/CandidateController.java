package com.ilyaselmabrouki.candidate_service.candidate;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService service;

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(
            @RequestBody @Valid CandidateRequest candidate
    ){
        return ResponseEntity.ok(service.createCandidate(candidate));
    }

    @PutMapping
    public ResponseEntity<Void> updateCandidate(
            @RequestBody @Valid CandidateRequest candidate
    ){
        service.updateCandidate(candidate);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidates(){
        return ResponseEntity.ok(service.getCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> findCandidate(@PathVariable Integer id){
        return ResponseEntity.ok(service.findCandidate(id));
    }

    @PostMapping("/ids")
    public ResponseEntity<List<CandidateResponse>> findCandidatesByIds(@RequestBody List<Integer> candidateIds){
        return ResponseEntity.ok(service.findCandidatesByIds(candidateIds));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Integer id){
        service.deleteCandidate(id);
        return ResponseEntity.accepted().build();
    }

}
