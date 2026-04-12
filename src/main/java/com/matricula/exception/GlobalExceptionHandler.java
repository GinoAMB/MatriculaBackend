package com.matricula.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //VALIDACIONES
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return buildResponse(
                "Error de validación",
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    //BAD REQUEST
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        return buildResponse(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    // NOT FOUND
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return buildResponse(ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    // UNAUTHORIZED
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        return buildResponse(ex.getMessage(), null, HttpStatus.UNAUTHORIZED);
    }

    // ERROR GENERAL (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return buildResponse("Error interno del servidor", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // METODO CENTRAL
    private ResponseEntity<ErrorResponse> buildResponse(String message, List<String> errors, HttpStatus status) {

        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                errors,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, status);
    }
}