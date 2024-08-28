package com.ilyaselmabrouki.application_service.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    List<Application> findApplicationsByCandidateId(Integer candidateId);
}
