package com.matricula.dto.rol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RolUpdateRequestDTO(

        @Schema(example = "1")
        @NotNull(message = "El id es obligatorio")
        Integer idRol,

        @Schema(example = "ADMIN")
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre
) {}