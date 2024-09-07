package com.ilyaselmabrouki.test_service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestNotFoundException extends RuntimeException{
    private final String msg;
}
