package com.matricula.dto.matricula;

public record AlumnoResponseDTO(

        Integer idMatricula,
        Integer idAlumno,
        String nombre,
        String apellidos,
        String tipoDocumento,
        String numeroDocumento,
        String nivel,
        String grado,
        String seccion

) {}
