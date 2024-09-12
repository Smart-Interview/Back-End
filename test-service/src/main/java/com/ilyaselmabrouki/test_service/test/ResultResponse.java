package com.ilyaselmabrouki.test_service.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultResponse {
    private Integer numberQuestions;
    private Integer result;
}
