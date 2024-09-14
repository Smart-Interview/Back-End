package com.ilyaselmabrouki.report_service.report;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private final ReportRepository repository;
    private final ReportMapper mapper;

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
}
