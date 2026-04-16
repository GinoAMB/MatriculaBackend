package com.matricula.dto.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeccionRequestDTO(

        @Schema(
                description = "Nombre de la sección (ejemplo: A, B, C)",
                example = "A"
        )
        @NotBlank(message = "El nombre de la sección es obligatorio")
        String nombre,

        @Schema(
                description = "ID del nivel (1 = PRIMARIA, 2 = SECUNDARIA)",
                example = "1"
        )
        @NotNull(message = "El nivel es obligatorio")
        Integer idNivel,

        @Schema(
                description = "ID del grado correspondiente al nivel",
                example = "2"
        )
        @NotNull(message = "El grado es obligatorio")
        Integer idGrado
) {}
