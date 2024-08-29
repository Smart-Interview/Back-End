package com.ilyaselmabrouki.application_service.analyse;

import com.ilyaselmabrouki.application_service.application.ApplicationStatus;
import lombok.Data;

@Data
public class AnalyseResponse {
    private Integer id;
    private ApplicationStatus status;
}
