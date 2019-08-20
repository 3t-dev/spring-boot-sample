package com.example.demo.exception.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorBody {
    private String code;
    private String message;
}
