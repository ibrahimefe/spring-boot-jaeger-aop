package com.dfx.thought.leadership.article.jaegeraop.configuration.handler;

import com.dfx.thought.leadership.article.jaegeraop.configuration.GenericException;
import com.dfx.thought.leadership.article.jaegeraop.data.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String GENERIC_ERR = "Generic Error Occurred!";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.getMessage());
        ErrorResponse response = new ErrorResponse();
        response.setSuccess(false);
        response.setSourceMessage(GENERIC_ERR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException genericException) {
        ErrorResponse response = new ErrorResponse();
        response.setSuccess(false);
        response.setSourceMessage(genericException.getSourceMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
