package com.digisphere.propertize.infra.ErrorHandler;

import org.springframework.validation.FieldError;

public record DataValidationError(String field, String message) {

    public DataValidationError(FieldError field){
        this(field.getField(), field.getDefaultMessage());
    };
}
