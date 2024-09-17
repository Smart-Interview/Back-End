package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.file.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationMapper {

    private final FileService fileService;

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
                null,
                application.getCv().getId(),
                application.getStatus(),
                application.getCreatedAt()
        );
    }
}
