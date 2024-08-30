package com.ilyaselmabrouki.test_service.question;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private List<String> question;
    private String answer;
    private Integer score;
}
