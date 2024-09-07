package com.ilyaselmabrouki.candidate_service.candidate;

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
    private String userName;
    private String email;
}
