package com.example.firstdemo;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
