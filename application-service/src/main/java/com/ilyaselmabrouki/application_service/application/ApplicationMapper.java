package com.ilyaselmabrouki.application_service.application;

import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {

    public Application toApplication(ApplicationRequest request){
        return Application.builder()
                .id(request.getId())
                .candidateId(request.getCandidateId())
                .offerId(request.getOfferId())
                .status(ApplicationStatus.UNDER_REVIEW)
                .build();
    }

    public ApplicationResponse fromApplication(Application application){
        return new ApplicationResponse(
                application.getId(),
                application.getCandidateId(),
                application.getOfferId(),
                application.getCv().getPath(),
                application.getStatus()
        );
    }
}
