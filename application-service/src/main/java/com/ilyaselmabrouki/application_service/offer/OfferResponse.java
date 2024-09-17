package com.ilyaselmabrouki.application_service.offer;

import lombok.Data;

@Data
public class OfferResponse {
    private Integer id;
    private String title;
    private String descriptionFilePath;
    private String requirements;
    private Integer recruiter;
}
