package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.candidate.CandidateClient;
import com.ilyaselmabrouki.application_service.exception.CandidateNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final CandidateClient candidateClient;

    public Integer createApplication(ApplicationRequest request) {
        //Check candidate ID
        candidateClient.findCandidateById(request.getCandidateId())
                .orElseThrow(()-> new CandidateNotFoundException("No Candidate exist with the ID provided"));

        //Check Offer ID

        //Add application in DB
        var application = repository.save(mapper.toApplication(request));
        return application.getId();
    }

    public List<ApplicationResponse> getAllApplications() {
        return repository.findAll()
                .stream()
                .map(mapper::fromApplication)
                .collect(Collectors.toList());
    }

    public ApplicationResponse findApplication(Integer id) {
        return repository.findById(id)
                .map(mapper::fromApplication)
                .orElseThrow(()-> new EntityNotFoundException("No Application found"));
    }

    public List<ApplicationResponse> findApplicationsByCandidateId(Integer candidateId) {
        return repository.findApplicationsByCandidateId(candidateId)
                .stream()
                .map(mapper::fromApplication)
                .collect(Collectors.toList());
    }

}
