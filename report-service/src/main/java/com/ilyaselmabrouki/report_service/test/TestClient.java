package com.ilyaselmabrouki.report_service.test;

import com.ilyaselmabrouki.report_service.application.ApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "test-service",
        url = "${application.config.test-url}"
)
public interface TestClient {
    @GetMapping(value = "/offer")
    List<TestResponse> findTestsByOfferId(@RequestParam Integer offerId);
}
