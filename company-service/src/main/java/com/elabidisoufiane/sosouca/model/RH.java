package com.elabidisoufiane.sosouca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class RH {
    @Id @GeneratedValue
    private Integer id;
    private String userName;
    private String email;
    private String code;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
