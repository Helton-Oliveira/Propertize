package com.digisphere.propertize.infra.ErrorHandler;

public class CustomException extends RuntimeException {
    public CustomException(String error) {
        super(error);
    }
}
