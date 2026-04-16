package com.matricula.dto.academico;

import java.util.List;

public record NivelResponseDTO(
        Integer idNivel,
        String nombre,
        List<GradoResponseDTO> grados
) {}
