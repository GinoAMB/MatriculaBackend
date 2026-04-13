package com.matricula.dto.periodo_escolar;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PeriodoRequestDTO(

        @Schema(
                example = "2025",
                description = "Año del periodo escolar"
        )
        @NotNull(message = "El año es obligatorio")
        @Min(value = 2000, message = "El año debe ser mayor o igual a 2000")
        @Max(value = 2100, message = "El año debe ser menor o igual a 2100")
        Integer anio,

        @Schema(
                example = "2025-03-01",
                description = "Fecha de inicio del periodo escolar (formato: yyyy-MM-dd)"
        )
        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio,

        @Schema(
                example = "2025-12-20",
                description = "Fecha de fin del periodo escolar (formato: yyyy-MM-dd)"
        )
        @NotNull(message = "La fecha de fin es obligatoria")
        LocalDate fechaFin

) {}