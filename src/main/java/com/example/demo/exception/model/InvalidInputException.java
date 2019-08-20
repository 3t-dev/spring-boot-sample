package com.example.demo.exception.model;

public class InvalidInputException extends BaseException {
    public InvalidInputException(String code, String message) {
        super(code, message);
    }
}
