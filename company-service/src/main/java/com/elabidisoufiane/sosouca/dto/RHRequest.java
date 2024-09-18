package com.elabidisoufiane.sosouca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class RHRequest {
    private Integer id;
    private String userName;
    private String email;
    private String code;
    private Integer company;
}
