package com.ilyaselmabrouki.candidate_service.candidate;

import com.ilyaselmabrouki.candidate_service.exception.CandidateNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateService {

    public final CandidateMapper mapper;
    public final CandidateRepository repository;
    public Integer createCandidate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String username = jwt.getClaim("preferred_username");
        String email = jwt.getClaim("email");

        Candidate candidate = new Candidate();
        candidate.setUserName(username);
        candidate.setEmail(email);
        return repository.save(candidate).getId();
    }

    public void updateCandidate(CandidateRequest request) {
        var candidate = repository.findById(request.getId())
                .orElseThrow(()-> new CandidateNotFoundException("Cannot found Candidate"));
        mergerCandidate(candidate, request);
        repository.save(candidate);
    }

    private void mergerCandidate(Candidate candidate, CandidateRequest request){
        if (StringUtils.isNotBlank(request.getUserName())){
            candidate.setUserName(request.getUserName());
        }
        if (StringUtils.isNotBlank(request.getEmail())){
            candidate.setEmail(request.getEmail());
        }
    }

    public List<CandidateResponse> getCandidates() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCandidate)
                .collect(Collectors.toList());
    }

    public CandidateResponse findCandidate(Integer id) {
        var candidate = repository.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException("Cannot found Candidate"));
        return mapper.fromCandidate(candidate);
    }


    public void deleteCandidate(Integer id) {
        var candidate = repository.findById(id)
                .orElseThrow(()-> new CandidateNotFoundException("Cannot found Candidate"));
        repository.delete(candidate);
    }
}
