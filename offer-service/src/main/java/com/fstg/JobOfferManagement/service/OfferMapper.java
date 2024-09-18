package com.fstg.JobOfferManagement.service;

import com.fstg.JobOfferManagement.dto.JobOfferRequestDto;
import com.fstg.JobOfferManagement.dto.JobOfferResponseDto;
import com.fstg.JobOfferManagement.model.JobOffer;
import org.springframework.stereotype.Service;

@Service
public class OfferMapper {

    public JobOffer toJobOffer(JobOfferRequestDto request){
        if (request == null) return null;
        return JobOffer.builder()
                .id(request.getId())
                .title(request.getTitle())
                .company(request.getCompany())
                .deadline(request.getDeadline())
                .build();
    }

    public JobOfferResponseDto fromJobOffer(JobOffer jobOffer){
        return new JobOfferResponseDto(
                jobOffer.getId(),
                jobOffer.getTitle(),
                null,
                jobOffer.getDeadline()
        );
    }
}
