package com.ilyaselmabrouki.test_service.offer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OfferResponse {
    private Integer id;
    private String title;
    private CompanyResponse company;
    private LocalDate deadline;
}
