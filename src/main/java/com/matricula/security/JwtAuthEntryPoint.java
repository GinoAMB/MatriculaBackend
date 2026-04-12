package com.matricula.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException ex) throws IOException {
        writeError(response, HttpServletResponse.SC_UNAUTHORIZED,
                "unauthorized", "Token inválido o no proporcionado");
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {
        writeError(response, HttpServletResponse.SC_FORBIDDEN,
                "forbidden", "No tienes permisos para acceder a este recurso");
    }

    // Método reutilizable
    private void writeError(HttpServletResponse response, int status,
                            String code, String message) throws IOException {

        response.setStatus(status);
        response.setContentType("application/json");

        Map<String, String> error = new HashMap<>();
        error.put("code", code);
        error.put("message", message);

        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}