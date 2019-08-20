package com.example.demo.api;

import com.example.demo.api.dto.OrderRequestDto;
import com.example.demo.api.dto.OrderResponseDto;
import com.example.demo.exception.model.ApiErrorBody;
import com.example.demo.exception.model.FlatViolation;
import com.example.demo.exception.model.InvalidInputException;
import com.example.demo.feature.order.OrderModifyingUsecase;
import com.example.demo.feature.order.model.Order;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.common.Constants.ERROR_CODE_INVALID_INPUT;

@Api(tags = "orders")
@RestController
@RequestMapping("/orders")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderModifyingUsecase modifyingUsecase;

    @Autowired
    public OrderController(OrderModifyingUsecase modifyingUsecase) {
        this.modifyingUsecase = modifyingUsecase;
    }

    @ApiOperation(tags = "order", value = "create new order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The order is created successfully", response = OrderResponseDto.class),
            @ApiResponse(code = 400, message = "Invalid input", response = ApiErrorBody.class),
            @ApiResponse(code = 500, message = "Server error", response = ApiErrorBody.class)
    })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createNew(@RequestBody OrderRequestDto dto) {
        logger.info("request to create new order {}", dto);
        validateDto(dto);

        Order o = Order.builder()
                .code(dto.getCode())
                .product(dto.getProduct())
                .volume(dto.getVolume())
                .action(Order.Action.valueOf(dto.getAction()))
                .build();
        Order order = modifyingUsecase.addNewOrder(o);

        return ResponseEntity.ok(order);
    }

    private void validateDto(OrderRequestDto orderDto) throws InvalidInputException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<OrderRequestDto>> violations = validator.validate(orderDto, new Class[0]);

        Set<FlatViolation> flatObjs = violations.stream().map(
                (violation) -> FlatViolation.builder().fieldName(violation.getPropertyPath().toString()).message(violation.getMessage()).build()
        ).collect(Collectors.toSet());

        if (flatObjs.size() > 0) {
            throw new InvalidInputException(ERROR_CODE_INVALID_INPUT, (new Gson()).toJson(flatObjs));
        }
    }
}
