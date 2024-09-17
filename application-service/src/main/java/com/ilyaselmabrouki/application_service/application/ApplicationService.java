package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.analyse.AnalyseClient;
import com.ilyaselmabrouki.application_service.analyse.AnalyseResponse;
import com.ilyaselmabrouki.application_service.candidate.CandidateClient;
import com.ilyaselmabrouki.application_service.candidate.CandidateResponse;
import com.ilyaselmabrouki.application_service.exception.ApplicationNotFoundException;
import com.ilyaselmabrouki.application_service.exception.OfferNotFoundException;
import com.ilyaselmabrouki.application_service.file.FileService;
import com.ilyaselmabrouki.application_service.offer.OfferClient;
import com.ilyaselmabrouki.application_service.offer.OfferResponse;
import com.ilyaselmabrouki.application_service.report.ApplicationReponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final CandidateClient candidateClient;
    private final OfferClient offerClient;
    private final AnalyseClient analyseClient;
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
        savedApplication.setCv(fileService.uploadFile(request.getCv()));
        repository.save(savedApplication);

        //Add application in DB
        return savedApplication.getId();
    }

    private void analyzeCvAsync(Integer applicationId, Integer offerId, MultipartFile cv) {
        // Communicate with CV Analysis Service
        AnalyseResponse result = analyseClient.analyzeCv(applicationId, offerId, cv);

        // Update application status based on the analysis result
        updateApplicationStatus(result.getApplicationId(), result.getStatus());
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

    public ApplicationResponse findApplication(Integer id) {
        return repository.findById(id)
                .map(mapper::fromApplication)
                .orElseThrow(()-> new ApplicationNotFoundException("No Application found"));
    }

    public Page<ApplicationResponse> findApplicationsByCandidateId(Integer candidateId, int page, int size) {
        // Check candidate ID (add error handling if candidate not found)
        candidateClient.findCandidateById(candidateId);

        // Fetch applications
        Pageable pageable = PageRequest.of(page, size);
        Page<Application> applications = repository.findApplicationsByCandidateId(candidateId, pageable);

        // Fetch all offer responses in a single call (to avoid N+1 problem)
        List<Integer> offerIds = applications.stream()
                .map(Application::getOfferId)
                .collect(Collectors.toList());

        List<OfferResponse> offers = offerClient.findOffersByIds(offerIds);

        Map<Integer, OfferResponse> offerMap = offers.stream()
                .collect(Collectors.toMap(OfferResponse::getId, offer -> offer));

        // Map applications to responses
        return applications.map(application -> {
                    OfferResponse offerResponse = offerMap.get(application.getOfferId());
                    if (offerResponse == null) {
                        throw new OfferNotFoundException("Offer not found");
                    }
                    ApplicationResponse applicationResponse = mapper.fromApplication(application);
                    applicationResponse.setOffer(offerResponse);
                    return applicationResponse;
                });
    }

    public List<ApplicationReponse> findCandidatesByOfferIdAndStatus(Integer offerId) {
        // Check if the offer exists
        offerClient.findOfferById(offerId);

        // Define the statuses to check
        List<ApplicationStatus> statuses = List.of(
                ApplicationStatus.UNDER_REVIEW,
                ApplicationStatus.ACCEPTED,
                ApplicationStatus.REFUSED
        );

        List<ApplicationReponse> applicationResponses = new ArrayList<>();

        for (ApplicationStatus status : statuses) {
            // Retrieve applications for the given offer and status
            List<Application> applications = repository.findApplicationsByOfferIdAndStatus(offerId, status);

            // Extract candidate IDs from the applications
            List<Integer> candidateIds = applications.stream()
                    .map(Application::getCandidateId)
                    .collect(Collectors.toList());

            // Fetch all candidate details in a single request
            List<CandidateResponse> candidates = candidateClient.findCandidatesByIds(candidateIds);

            // Create ApplicationResponse for the current status
            ApplicationReponse applicationResponse = new ApplicationReponse(
                    status,           // Current status (UNDER_REVIEW, ACCEPTED, REJECTED)
                    candidates.size(),// Number of candidates for this status
                    candidates        // List of candidates
            );

            // Add the response to the list
            applicationResponses.add(applicationResponse);
        }
        return applicationResponses;
    }

}
