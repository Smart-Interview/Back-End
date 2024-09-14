package com.ilyaselmabrouki.report_service.report;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportRequest {
    private Integer id;
    @NotNull(message = "Offer should be present")
    private Integer offerId;
}
