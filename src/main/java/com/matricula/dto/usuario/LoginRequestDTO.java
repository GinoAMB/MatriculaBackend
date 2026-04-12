package com.matricula.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @Schema(example = "juan@gmail.com", description = "Correo electrónico del usuario")
        @NotBlank(message = "El correo no puede estar vacío")
        @Email(message = "El correo debe tener un formato válido")
        String correo,

        @Schema(example = "123456", description = "Contraseña del usuario")
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
        String password
) {
}
