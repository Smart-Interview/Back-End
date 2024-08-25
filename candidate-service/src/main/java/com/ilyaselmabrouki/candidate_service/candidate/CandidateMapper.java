package com.ilyaselmabrouki.candidate_service.candidate;

import org.springframework.stereotype.Service;

@Service
public class CandidateMapper {

    public Candidate toCandidate(CandidateRequest request){
        if (request == null) return null;
        return Candidate.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

    public CandidateResponse fromCandidate(Candidate candidate){
        return new CandidateResponse(
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getEmail()
        );
    }
}
