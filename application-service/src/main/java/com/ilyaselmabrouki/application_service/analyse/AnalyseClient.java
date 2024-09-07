package com.ilyaselmabrouki.application_service.analyse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "analyse-service",
        url = "${application.config.analyse-url}"
)
public interface AnalyseClient {
    @PostMapping(value = "/analyze-cv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    AnalyseResponse analyzeCv(@RequestPart("applicationId") Integer applicationId,
                              @RequestPart("offerId") Integer offerId,
                              @RequestPart("cv") MultipartFile cv);
}
