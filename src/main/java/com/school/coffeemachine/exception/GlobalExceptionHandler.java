package com.school.coffeemachine.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }
        ApiError body = new ApiError(400, "VALIDATION_ERROR", "Request validation failed", req.getRequestURI(), fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        ApiError body = new ApiError(404, "NOT_FOUND", ex.getMessage(), req.getRequestURI(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(DuplicateRecipeNameException.class)
    public ResponseEntity<ApiError> handleDuplicate(DuplicateRecipeNameException ex, HttpServletRequest req) {
        ApiError body = new ApiError(409, "DUPLICATE_RECIPE", ex.getMessage(), req.getRequestURI(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<ApiError> handleInsufficient(InsufficientInventoryException ex, HttpServletRequest req) {
        ApiError body = new ApiError(409, "INSUFFICIENT_INVENTORY", ex.getMessage(), req.getRequestURI(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
