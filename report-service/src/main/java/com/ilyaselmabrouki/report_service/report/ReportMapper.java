package com.ilyaselmabrouki.report_service.report;

import org.springframework.stereotype.Service;

@Service
public class ReportMapper {

    public Report toReport(ReportRequest report){
        return Report.builder()
                .id(report.getId())
                .offerId(report.getOfferId())
                .build();
    }

    public ReportResponse fromReport(Report report){
        return new ReportResponse(
                report.getId(),
                null,
                null
        );
    }
}
