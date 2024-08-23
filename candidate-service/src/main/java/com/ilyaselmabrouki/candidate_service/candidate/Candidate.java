package com.ilyaselmabrouki.candidate_service.candidate;

import com.ilyaselmabrouki.candidate_service.application.Application;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Candidate {
    @Id @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;
}
