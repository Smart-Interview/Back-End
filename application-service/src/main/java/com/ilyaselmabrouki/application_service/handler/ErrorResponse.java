package com.ilyaselmabrouki.application_service.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Map<String, String> errors;
}
