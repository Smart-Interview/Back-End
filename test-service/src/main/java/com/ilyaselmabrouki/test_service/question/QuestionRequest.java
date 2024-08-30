package com.ilyaselmabrouki.test_service.question;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionRequest {
    private Integer testId;
    private Integer offerId;
}
