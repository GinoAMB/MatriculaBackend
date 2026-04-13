package com.matricula.dto.periodo_escolar;

import java.time.LocalDate;

public record PeriodoResponseDTO(
        Integer idPeriodo,
        Integer anio,
        LocalDate fechaInicio,
        LocalDate fechaFin
) {}
