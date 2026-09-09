package com.matricula.dto.matricula;

public record FamiliarDetalleResponseDTO(

        Integer idRelacion,
        Integer idFamiliar,

        String nombre,
        String apellidos,

        Integer idTipoDocumento,
        String tipoDocumento,
        String numeroDocumento,

        String direccion,
        String celular,

        Integer idTipoRelacion,
        String tipoRelacion,

        Boolean esApoderado,
        Boolean esFallecido
) {
}
