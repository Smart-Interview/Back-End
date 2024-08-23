package com.ilyaselmabrouki.candidate_service.handler;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ErrorResponse {
    private Map<String, String> errors;
}
