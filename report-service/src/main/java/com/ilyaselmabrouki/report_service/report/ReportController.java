package com.ilyaselmabrouki.report_service.report;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<Integer> createReport(@RequestBody @Valid ReportRequest request){
        return ResponseEntity.ok(reportService.createReport(request));
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports(){
        return ResponseEntity.ok(reportService.findAll());
    }
}
