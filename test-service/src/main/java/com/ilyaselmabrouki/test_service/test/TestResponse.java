package com.ilyaselmabrouki.test_service.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestResponse {
    private Integer id;
    private Integer candidateId;
    private Integer offerId;
    private Integer applicationId;
    private Integer score;
}
