package com.elabidisoufiane.sosouca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CEO {
    @Id @GeneratedValue
    private Integer id;
    private String userName;
    private String email;
    @OneToMany(mappedBy = "ceo", cascade = CascadeType.ALL)
    private List<Company> companies;
}
