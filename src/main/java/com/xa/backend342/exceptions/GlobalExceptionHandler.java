package com.xa.backend342.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.xa.backend342.payloads.ApiResponse;

@ControllerAdvice
// When annotated with @ControllerAdvice, 
// a class can define methods that handle exceptions thrown by any controller method.
// This means you don't have to repeat the error handling logic in every controller.
public class GlobalExceptionHandler {

    // When a controller method throws an exception, Spring checks if any method 
    // in the current controller has an @ExceptionHandler annotation for that exception type.
    // If no local handlers are found, Spring will look for @ControllerAdvice classes. 
    // If a matching @ExceptionHandler is found in any @ControllerAdvice, that method will be invoked.
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Exception>> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(ApiResponse.error(ex.getStatusCode().value(),ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Exception>> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex));
    }
}
