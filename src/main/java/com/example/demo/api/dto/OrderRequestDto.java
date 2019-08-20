package com.example.demo.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel
public class OrderRequestDto {
    @NotEmpty
    private String code;
    @NotEmpty
    private String product;
    @NotNull
    private Double volume;

    private String action;
}
