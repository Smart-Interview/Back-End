package com.ilyaselmabrouki.application_service.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Page<Application> findApplicationsByCandidateId(Integer candidateId, Pageable pageable);
    List<Application> findApplicationsByOfferIdAndStatus(Integer offerId, ApplicationStatus status);
}
