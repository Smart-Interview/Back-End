package com.fstg.JobOfferManagement.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OfferNotFoundException extends RuntimeException{
    private final String msg;
}
