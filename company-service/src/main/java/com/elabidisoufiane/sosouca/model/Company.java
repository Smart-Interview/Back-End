package com.elabidisoufiane.sosouca.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Company {
    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String industry;
    private String location;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<RH> rhList;
    @ManyToOne
    @JoinColumn(name = "ceo_id")
    private CEO ceo;
}
