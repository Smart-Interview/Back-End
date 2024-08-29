package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.candidate.CandidateClient;
import com.ilyaselmabrouki.application_service.exception.CandidateNotFoundException;
import com.ilyaselmabrouki.application_service.file.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final CandidateClient candidateClient;
    private final FileService fileService;

    public Integer createApplication(ApplicationRequest request) throws IOException {
        //Check candidate ID
        candidateClient.findCandidateById(request.getCandidateId())
                .orElseThrow(()-> new CandidateNotFoundException("No Candidate exist with the ID provided"));

        //Check Offer ID

        //Store File
        Application application = mapper.toApplication(request);
        application.setCv(fileService.uploadFile(request.getCv()));

        //Add application in DB
        var savedApplication = repository.save(application);
        return savedApplication.getId();
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
