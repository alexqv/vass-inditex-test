package com.inditex.zara.price.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleException(Exception exception) {
    	System.out.println(exception.getMessage());
        return ResponseEntity
        		.status(INTERNAL_SERVER_ERROR)
        		.body(INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
