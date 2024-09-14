package com.ilyaselmabrouki.report_service.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor @Builder
public class ReportResponse {
    private Integer id;
    private Integer offerId;
}
