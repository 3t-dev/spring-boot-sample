package com.example.demo.exception.model;

public class BaseException extends RuntimeException {
    private String code;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
        this.code = "";
    }

    public BaseException() {
        super("");
        this.code = "";
    }

    public String getCode() {
        return this.code;
    }
}
