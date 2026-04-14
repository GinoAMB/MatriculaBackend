package com.matricula.dto.recuperacion_password;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ValidarTokenDTO(

        @Schema(
                description = "Token enviado al correo del usuario",
                example = "123e4567-e89b-12d3-a456-426614174000",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "El token es obligatorio")
        String token
) {}
