package com.matricula.dto.documento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DocumentoUpdateRequestDTO(

        @Schema(example = "1")
        @NotNull(message = "El id es obligatorio")
        Integer idTipo,

        @Schema(example = "DNI")
        @NotBlank(message = "El nombre no puede estar vacio")
        @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
        String nombre
) {}
