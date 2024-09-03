package com.ilyaselmabrouki.test_service.question;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private String question;
    private List<String> options;
    private String answer;
    private Integer score;
}