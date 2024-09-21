package com.ilyaselmabrouki.report_service.report;

import com.ilyaselmabrouki.report_service.application.ApplicationClient;
import com.ilyaselmabrouki.report_service.test.TestClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository repository;
    private final ReportMapper mapper;
    private final ApplicationClient applicationClient;
    private final TestClient testClient;

    public ReportResponse createReport(ReportRequest request) {
        Report report = mapper.toReport(request);
        Report saved = repository.save(report);
        ReportResponse reportResponse = mapper.fromReport(saved);
        reportResponse.setApplications(applicationClient.findApplicationsByOfferId(report.getOfferId()));
        reportResponse.setTests(testClient.findTestsByOfferId(report.getOfferId()));
        return reportResponse;
    }

    public List<ReportResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromReport)
                .collect(Collectors.toList());
    }
}
