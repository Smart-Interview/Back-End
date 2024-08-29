package com.ilyaselmabrouki.application_service.analyse;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AnalyseRequest {
    private Integer offerId;
    private String cvPath;
}
