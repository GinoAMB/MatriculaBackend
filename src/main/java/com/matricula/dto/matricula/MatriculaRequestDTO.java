package com.matricula.dto.matricula;

import java.time.LocalDate;

public record MatriculaRequestDTO(

        AlumnoRequestDTO alumno,

        FamiliarRequestDTO padre,
        FamiliarRequestDTO madre,
        FamiliarRequestDTO apoderadoExterno,

        Integer idSeccion,
        Integer idPeriodo,
        Integer idEstado,
        LocalDate fechaMatricula

) {}
