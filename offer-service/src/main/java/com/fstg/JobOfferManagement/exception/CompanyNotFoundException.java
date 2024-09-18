package com.fstg.JobOfferManagement.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyNotFoundException extends RuntimeException{
    private final String msg;
}
