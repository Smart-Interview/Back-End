package com.ilyaselmabrouki.test_service.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findAllByCandidateId(Integer id);
}
