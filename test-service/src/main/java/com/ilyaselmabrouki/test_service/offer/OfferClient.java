package com.ilyaselmabrouki.test_service.offer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "offer-service",
        url = "${application.config.offer-url}"
)
public interface OfferClient {

    @GetMapping("/{id}")
    Optional<OfferResponse> findOfferById(@PathVariable("id") Integer id);
}
