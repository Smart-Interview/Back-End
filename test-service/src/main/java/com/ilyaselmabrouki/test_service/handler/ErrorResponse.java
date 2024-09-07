package com.ilyaselmabrouki.test_service.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Map<String, String> errors;
}
