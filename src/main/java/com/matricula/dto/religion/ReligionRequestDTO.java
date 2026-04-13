package com.matricula.dto.religion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReligionRequestDTO(
        @Schema(example = "Catolica")
        @NotBlank(message = "El nombre no puede estar vacio")
        @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
        String nombre
) {}
