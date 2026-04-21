package com.matricula.dto.matricula;

public record FamiliarBasicoRequestDTO(

        String nombre,
        String apellidos,
        Integer idTipoDocumento,
        String numeroDocumento,
        String direccion,
        String celular

) {}
