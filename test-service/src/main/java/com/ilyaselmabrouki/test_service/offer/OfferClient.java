package com.ilyaselmabrouki.test_service.offer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "offer-service",
        url = "${application.config.offer-url}"
)
public interface OfferClient {

    @GetMapping("/id/{id}")
    Optional<OfferResponse> findOfferById(@PathVariable("id") Integer id);

    @PostMapping("/ids")
    List<OfferResponse> findOffersByIds(@RequestBody List<Integer> offerIds);
}
