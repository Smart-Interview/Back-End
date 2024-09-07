package com.ilyaselmabrouki.candidate_service.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private Map<String, String> errors;
}
