package com.matricula.dto.tipo_relacion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoRequestDTO(
        @Schema(example = "PADRE")
        @NotBlank(message = "El nombre no puede estar vacio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre
) {}
