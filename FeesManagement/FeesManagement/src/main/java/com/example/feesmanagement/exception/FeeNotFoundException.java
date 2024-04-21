package com.example.feesmanagement.exception;

public class FeeNotFoundException extends RuntimeException {

    public FeeNotFoundException(String message) {
        super(message);
    }
}
