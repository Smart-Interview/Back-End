package com.ilyaselmabrouki.report_service.application;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(
       name = "application-service",
        url = "${application.config.application-url}"
)
public interface ApplicationClient {
    @GetMapping(value = "/offer")
    List<ApplicationResponse> findApplicationsByOfferId(@RequestParam Integer offerId);
}
