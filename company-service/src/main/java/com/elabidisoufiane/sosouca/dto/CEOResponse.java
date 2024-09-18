package com.elabidisoufiane.sosouca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class CEOResponse {
    private Integer id;
    private String userName;
    private String email;
}
