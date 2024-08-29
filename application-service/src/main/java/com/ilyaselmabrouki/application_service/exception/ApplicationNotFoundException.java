package com.ilyaselmabrouki.application_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationNotFoundException extends RuntimeException {
    private final String msg;
}
