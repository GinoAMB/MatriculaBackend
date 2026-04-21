package com.matricula.dto.matricula;

public record AlumnoResponseDTO(

        Integer idAlumno,
        String nombre,
        String apellidos,

        String nivel,
        String grado,
        String seccion

) {}
