package com.ilyaselmabrouki.application_service.offer;

import lombok.Data;

@Data
public class OfferResponse {
    private Integer id;
    private String title;
    private String description_path;
    private Integer company;
}
