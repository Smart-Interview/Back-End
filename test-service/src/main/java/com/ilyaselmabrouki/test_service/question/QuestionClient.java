package com.ilyaselmabrouki.test_service.question;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "question-service",
        url = "${application.config.question-url}"
)
public interface QuestionClient {
    @GetMapping
    List<QuestionResponse> getQuestions(@RequestBody QuestionRequest request);
}
