package com.ilyaselmabrouki.application_service.analyse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(
//        name = "analyse-service",
//        url = "${application.config.analyse-url}"
//)
public interface AnalyseClient {
    @PostMapping("/analyze-cv")
    AnalyseResponse analyzeCv(@RequestBody AnalyseRequest request);
}
