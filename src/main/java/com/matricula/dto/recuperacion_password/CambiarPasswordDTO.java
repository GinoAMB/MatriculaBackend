package com.matricula.dto.recuperacion_password;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CambiarPasswordDTO(

        @Schema(
                description = "Token enviado al correo del usuario",
                example = "123e4567-e89b-12d3-a456-426614174000",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "El token es obligatorio")
        String token,

        @Schema(
                description = "Nueva contraseña del usuario",
                example = "password123",
                minLength = 6,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "La nueva contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String nuevoPassword
) {}