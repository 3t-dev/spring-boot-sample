package com.example.demo.exception.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlatViolation {
    private String fieldName;
    private String message;
}
