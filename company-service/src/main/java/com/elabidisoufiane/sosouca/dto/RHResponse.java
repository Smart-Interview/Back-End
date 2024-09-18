package com.elabidisoufiane.sosouca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RHResponse {
    private Integer id;
    private String userName;
    private String email;
    private Integer company;
}
