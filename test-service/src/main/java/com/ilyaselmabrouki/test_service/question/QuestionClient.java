package com.ilyaselmabrouki.test_service.question;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "question-service",
        url = "${application.config.question-url}"
)
public interface QuestionClient {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<QuestionResponse> getQuestions(@RequestBody QuestionRequest request);
}
