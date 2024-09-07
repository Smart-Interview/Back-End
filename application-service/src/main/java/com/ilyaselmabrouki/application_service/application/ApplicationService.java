package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.analyse.AnalyseClient;
import com.ilyaselmabrouki.application_service.analyse.AnalyseResponse;
import com.ilyaselmabrouki.application_service.candidate.CandidateClient;
import com.ilyaselmabrouki.application_service.exception.ApplicationNotFoundException;
import com.ilyaselmabrouki.application_service.file.FileService;
import com.ilyaselmabrouki.application_service.offer.OfferClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final CandidateClient candidateClient;
    private final OfferClient offerClient;
    //private final AnalyseClient analyseClient;
    private final FileService fileService;

    public Integer createApplication(ApplicationRequest request) throws IOException {
        //Check candidate ID
        candidateClient.findCandidateById(request.getCandidateId());

        //Check Offer ID
        offerClient.findOfferById(request.getOfferId());

        //Save Application in DB
        Application application = mapper.toApplication(request);
        var savedApplication = repository.save(application);

        //Trigger CV Analysis (asynchronously)
        analyzeCvAsync(savedApplication.getId(), request.getOfferId(), request.getCv());

        //Store File
        application.setCv(fileService.uploadFile(request.getCv()));

        //Add application in DB
        return savedApplication.getId();
    }

    private void analyzeCvAsync(Integer applicationId, Integer offerId, MultipartFile cv) {
        CompletableFuture.runAsync(() -> {
            try {
                // Communicate with CV Analysis Service
                AnalyseResponse result = analyseClient.analyzeCv(applicationId, offerId, cv);

                // Update application status based on the analysis result
                updateApplicationStatus(result.getApplicationId(), result.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void updateApplicationStatus(Integer applicationId, ApplicationStatus status) {
        Application application = repository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application not found"));

        if (status == ApplicationStatus.ACCEPTED) {
            application.setStatus(ApplicationStatus.ACCEPTED);
        } else {
            application.setStatus(ApplicationStatus.REFUSED);
        }

        repository.save(application);
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
                .orElseThrow(()-> new ApplicationNotFoundException("No Application found"));
    }

    public List<ApplicationResponse> findApplicationsByCandidateId(Integer candidateId) {
        return repository.findApplicationsByCandidateId(candidateId)
                .stream()
                .map(mapper::fromApplication)
                .collect(Collectors.toList());
    }

}
