package com.ilyaselmabrouki.application_service.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ilyaselmabrouki.application_service.application.Application;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
public class File {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String type;
    private String path;
    @OneToOne(mappedBy = "cv")
    private Application application;
}
