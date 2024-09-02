package com.ilyaselmabrouki.test_service.application;

import com.ilyaselmabrouki.test_service.offer.OfferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "application-service",
        url = "${application.config.application-url}"
)
public interface ApplicationClient {
    @GetMapping("/{id}")
    Optional<ApplicationResponse> findApplicationById(@PathVariable("id") Integer id);
}
