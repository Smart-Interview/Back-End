package com.ilyaselmabrouki.application_service.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;

    public Integer createApplication(ApplicationRequest request) {
    }

    public List<ApplicationResponse> getAllApplications() {
    }

    public ApplicationResponse findApplication(Integer id) {
    }

    public void updateApplication(ApplicationRequest application) {
    }

    public List<ApplicationResponse> findApplicationsByCandidateId(Integer candidateId) {
    }

}
