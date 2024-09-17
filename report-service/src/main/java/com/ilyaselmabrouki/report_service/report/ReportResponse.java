package com.ilyaselmabrouki.report_service.report;

import com.ilyaselmabrouki.report_service.application.ApplicationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor @Builder
public class ReportResponse {
    private Integer id;
    private List<ApplicationResponse> applications;
}
