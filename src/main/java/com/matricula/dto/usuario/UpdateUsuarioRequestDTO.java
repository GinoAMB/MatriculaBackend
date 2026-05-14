package com.matricula.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUsuarioRequestDTO(

        @Schema(example = "Juan", description = "Nombre del usuario")
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Schema(example = "Pérez García", description = "Apellidos del usuario")
        @NotBlank(message = "Los apellidos no pueden estar vacíos")
        @Size(min = 2, max = 80, message = "Los apellidos deben tener entre 2 y 80 caracteres")
        String apellidos,

        @Schema(example = "juan@gmail.com", description = "Correo electrónico válido")
        @NotBlank(message = "El correo no puede estar vacío")
        @Email(message = "El correo debe tener un formato válido")
        String correo,

        @Schema(example = "1", description = "ID del rol del usuario")
        @NotNull(message = "El rol es obligatorio")
        Integer idRol,

        @Schema(
                example = "123456",
                description = "Nueva contraseña del usuario. Si no se envía, no se actualizará."
        )
        @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
        String password,

        @Schema(example = "true", description = "Estado del usuario")
        boolean estado

) {}