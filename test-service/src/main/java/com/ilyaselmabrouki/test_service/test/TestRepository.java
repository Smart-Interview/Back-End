package com.ilyaselmabrouki.test_service.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    Page<Test> findAllByCandidateId(Integer id, Pageable pageable);

    List<Test> findAllByOfferId(Integer id, Sort sort);
}
