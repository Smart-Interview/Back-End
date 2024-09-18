package com.elabidisoufiane.sosouca.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CEONotFoundException extends RuntimeException{
    private final String msg;
}
