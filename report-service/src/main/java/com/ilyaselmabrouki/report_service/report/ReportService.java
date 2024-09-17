package com.ilyaselmabrouki.report_service.report;

import com.ilyaselmabrouki.report_service.application.ApplicationClient;
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

    public Integer createReport(ReportRequest request) {
        Report report = mapper.toReport(request);
        return repository.save(report).getId();
    }

    public List<ReportResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromReport)
                .collect(Collectors.toList());
    }

    public ReportResponse getReportById(Integer id) {
        Report report = repository.findById(id).get();
        ReportResponse reportResponse = mapper.fromReport(report);
        reportResponse.setApplications(applicationClient.findApplicationsByOfferId(report.getOfferId()));
        return reportResponse;
    }
}
