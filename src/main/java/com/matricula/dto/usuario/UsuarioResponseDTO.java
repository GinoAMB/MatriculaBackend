package com.matricula.dto.usuario;

public record UsuarioResponseDTO(
         Integer idUsuario,
         String nombres,
         String apellidos,
         String correo,
         Integer idRol,
         String rol,
         Boolean estado
) {
}
