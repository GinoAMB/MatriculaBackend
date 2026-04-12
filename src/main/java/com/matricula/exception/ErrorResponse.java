package com.matricula.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        String message,
        List<String> errors,
        LocalDateTime timestamp
) {}