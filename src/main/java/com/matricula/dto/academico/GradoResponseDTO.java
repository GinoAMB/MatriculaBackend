package com.matricula.dto.academico;

import java.util.List;

public record GradoResponseDTO(
        Integer idGrado,
        String nombre,
        List<SeccionResponseDTO> secciones
) {}
