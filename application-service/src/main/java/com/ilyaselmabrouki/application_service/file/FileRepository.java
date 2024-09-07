package com.ilyaselmabrouki.application_service.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Integer> {

    Optional<File> findByName(String name);
}
