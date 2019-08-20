package com.example.demo.api.dto;

import com.example.demo.common.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@ApiModel
public class OrderResponseDto {
    private Long id;
    private String code;
    private String product;
    private Double volume;

    private String action;
    @ApiModelProperty(example = "2019-07-12T04:09:15.551Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_ISO_FORMAT)
    private Date createdDate;
    @ApiModelProperty(example = "2019-07-12T04:09:15.551Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_ISO_FORMAT)
    private Date updatedDate;
}
