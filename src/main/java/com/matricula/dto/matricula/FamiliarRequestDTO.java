package com.matricula.dto.matricula;

public record FamiliarRequestDTO(

        FamiliarBasicoRequestDTO datos,

        Integer idTipoRelacion,

        Boolean esApoderado,
        Boolean esFallecido

) {}
