package com.example.order_service.exception;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

import lombok.Data;

@ControllerAdvice
public class GlobalExceptionHandler {

    // === Response Model ===
    @Data
    static class ErrorResponse {
        private final boolean success = false;
        private final OffsetDateTime timestamp = OffsetDateTime.now();
        private String message;
        private Object details;

        public ErrorResponse(String message, Object details) {
            this.message = message;
            this.details = details;
        }
    }

    // === 1) Not found / business ===
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {

        // Detectar errores comunes
        if (ex.getMessage() != null && ex.getMessage().contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(ex.getMessage(), null));
        }

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(ex.getMessage(), null));
    }

    // === 2) Validaciones con @Valid ===
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        var errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Validation failed", errores));
    }

    // === 3) Enums inválidos / JSON mal formado ===
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadJson(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Malformed JSON or invalid enum value", ex.getLocalizedMessage()));
    }

    // === 4) Fallback ===
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Unexpected server error", ex.getMessage()));
    }
}
