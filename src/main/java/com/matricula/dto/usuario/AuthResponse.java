package com.matricula.dto.usuario;

public record AuthResponse(
        Integer id,
        String name,
        String role,
        String token
) {}
