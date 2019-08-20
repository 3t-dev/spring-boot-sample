package com.example.demo.exception;

import com.example.demo.exception.model.ApiErrorBody;
import com.example.demo.exception.model.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static com.example.demo.common.Constants.ERROR_CODE_INTERNAL_SERVER;

@RestControllerAdvice
public class RESTExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RESTExceptionHandler.class);

    /**
     * 400
     */
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Object> handleBaseException(BaseException ex) {
        logger.error("{}", ex);
        ApiErrorBody dto = ApiErrorBody.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(dto);
    }

    /**
     * 500 Internal server error
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.error("{}", ex);
        ApiErrorBody dto = ApiErrorBody.builder()
                .code(ERROR_CODE_INTERNAL_SERVER)
                .message(ex.getLocalizedMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(dto);
    }
}
